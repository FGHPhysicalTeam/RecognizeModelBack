#!/usr/bin/env python
#-*- coding:UTF-8 -*-（添加）

#coding:utf-8
import tensorflow as tf
import numpy as np
from PIL import Image

SIZE = 784
WIDTH = 28
HEIGHT = 28
NUM_CLASSES = 20

#需要修改
SAVER_DIR = "/Users/fengyushan/githubProject/RecognizeModelBack/PicToLabel/char_model/"
path = "/Users/fengyushan/githubProject/RecognizeModelBack/connetModelAndFrontend/src/pic/test.png"

filename = 'test.png'
LETTERS_DIGITS = (
"+","-",".","0","1","2","3","4","5","6","7","8","9","=","B","E","q","v","θ","√")
license_num = ""
count = 0
image_count = 0

x = tf.placeholder(tf.float32, shape=[None, SIZE])
saver = tf.train.import_meta_graph("%smy_resnet.ckpt.meta" % (SAVER_DIR))
x_image = tf.reshape(x, [-1, WIDTH, HEIGHT, 1])


# 定义对应维数和各维长度的数组
val_images = [[0]*SIZE for i in range(1)]


# 定义卷积函数
def conv_layer(inputs, W, b, conv_strides, kernel_size, pool_strides, padding):
    L1_conv = tf.nn.conv2d(inputs, W, strides=conv_strides, padding=padding)
    L1_relu = tf.nn.relu(L1_conv + b)
    return tf.nn.max_pool(L1_relu, ksize=kernel_size, strides=pool_strides, padding='SAME')


# 定义全连接层函数
def full_connect(inputs, W, b):
    return tf.nn.relu(tf.matmul(inputs, W) + b)


index = 0

with tf.Session() as sess:
    sess.run(tf.global_variables_initializer())
    sess.run(tf.local_variables_initializer())

    # 第一个卷积层
    W_conv1 = sess.graph.get_tensor_by_name("W_conv1:0")
    b_conv1 = sess.graph.get_tensor_by_name("b_conv1:0")
    conv_strides = [1, 1, 1, 1]
    kernel_size = [1, 2, 2, 1]
    pool_strides = [1, 2, 2, 1]
    L1_pool = conv_layer(x_image, W_conv1, b_conv1, conv_strides, kernel_size, pool_strides, padding='SAME')

    # 第二个卷积层
    W_conv2 = sess.graph.get_tensor_by_name("W_conv2:0")
    b_conv2 = sess.graph.get_tensor_by_name("b_conv2:0")
    conv_strides = [1, 1, 1, 1]
    kernel_size = [1, 2, 2, 1]
    pool_strides = [1, 2, 2, 1]
    L2_pool = conv_layer(L1_pool, W_conv2, b_conv2, conv_strides, kernel_size, pool_strides, padding='SAME')

    # 全连接层
    W_fc1 = sess.graph.get_tensor_by_name("W_fc1:0")
    b_fc1 = sess.graph.get_tensor_by_name("b_fc1:0")
    h_pool2_flat = tf.reshape(L2_pool, [-1, 7 * 7 * 64])
    h_fc1 = full_connect(h_pool2_flat, W_fc1, b_fc1)

    # dropout
    keep_prob = tf.placeholder(tf.float32)

    h_fc1_drop = tf.nn.dropout(h_fc1, keep_prob)

    # readout层
    W_fc2 = sess.graph.get_tensor_by_name("W_fc2:0")
    b_fc2 = sess.graph.get_tensor_by_name("b_fc2:0")

    # 定义优化器和训练op
    y_conv = tf.matmul(h_fc1_drop, W_fc2) + b_fc2


    model_file = tf.train.latest_checkpoint(SAVER_DIR)
    saver.restore(sess, model_file)

    # path = "D:\\pycharmProjects\\test-lenet-5_2\\test-LeNet-5\\Pic\\test.png"


    img = Image.open(path)
    image_count = image_count + 1
    width = img.size[0]
    height = img.size[1]

    for h in range(0, height):
        for w in range(0, width):
            # 通过这样的处理，使数字的线条变细，有利于提高识别准确率
            # print(img.mode)
            r, g, b = img.getpixel((w, h))
            tmp = 0.299 * r + 0.587 * g + 0.114 * b
            if tmp > 230:
                # if r > 230 & g > 230 & b > 230:
                val_images[index][w + h * width] = 0
            else:
                val_images[index][w + h * width] = 1


    result = sess.run(y_conv, feed_dict={x: np.array(val_images), keep_prob: 1.0})

    max1 = 0
    max1_index = 0

    for j in range(NUM_CLASSES):
        if result[0][j] > max1:
            max1 = result[0][j]
            max1_index = j
            continue
    print(LETTERS_DIGITS[max1_index])
