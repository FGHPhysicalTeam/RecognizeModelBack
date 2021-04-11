import tensorflow as tf
import numpy as np
from keras.preprocessing.image import img_to_array, load_img
from keras.applications.vgg19 import preprocess_input, decode_predictions

# 13epcho
# charLabel = ['3', '1', '2', 'q', '-', '7', '9', '=', 'θ', '4', '+', '0', 'B', '6', '8', '5', 'v', 'E']

# 15epcho
charLabel = ['3', '1', '5', 'v', '-', 'B', '2', '8', '+', '6', '7', 'E', '4', 'θ', '9', 'q', '0', '=']

# 检测一张图片
def interence_one_image(num_pb_path):
    with tf.gfile.GFile(num_pb_path,'rb') as f:
        graph_def = tf.GraphDef() #
        graph_def.ParseFromString(f.read())

    with tf.Graph().as_default() as graph:
        tf.import_graph_def(graph_def,    # 计算图
                            input_map=None,
                            return_elements=None,
                            name="",
                            op_dict=None,
                            producer_op_list=None
                            )


    image_batch = graph.get_tensor_by_name("input_1_1:0") # 输入节点
    softmax = graph.get_tensor_by_name("dense_1_1/Softmax:0") # 输出节点

    image = load_img('/Users/fengyushan/githubProject/RecognizeModelBack/connetModelAndFrontend/src/pic/test.png', target_size=(48, 48))
    # convert the image pixels to a numpy array
    image = img_to_array(image)
    # reshape data for the model,输入的array需要有4个维度： samples, rows, columns, and channels
    image = image.reshape((1, image.shape[0], image.shape[1], image.shape[2]))
    # Keras 提供了一个叫做preprocess_input()的函数，用以对网络中的新收入进行预处理
    # image = preprocess_input(image)



    with tf.Session(graph=graph) as sess:
        results = sess.run(softmax,feed_dict={image_batch:image})
        results = np.squeeze(results)  # 删除单一维度

        top_k=results.argsort()[-1:]  # 排序获取数值最大的下标  [3]
        if results[top_k[0]] >= 0.2:
            print(charLabel[top_k[0]])
        else:
            print("UNKNOWN")
        # print("result:",top_k[0],results[top_k[0]])  # 打印最大的分类结果

num_pb_path = '/Users/fengyushan/githubProject/RecognizeModelBack/PicToLabel/char_model_new/mobilenet_64.pb'
interence_one_image(num_pb_path)

