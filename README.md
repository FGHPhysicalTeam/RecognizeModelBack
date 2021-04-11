# recognizeModelBackend
***前端调用后端模型，只需clone这个仓库***
## 起步
- 对于connectModelandFrontend
  - 修改GlobalVariable类中的pythonAddress, charPicToLabelwithNewModel, geoPicToLabel和picAddress
  - Entrance类为服务器入口，运行main启动服务器
- 对于PicToLabel
  - 修改geoPicToLabel.py的SAVER_DIR和path
  - 修改charPicToLabelwithNewModel.py的image和num_pb_path
  - 安装需要的包 pip install -r requirements.txt
    - tensorflow 1.13.1
    - numpy 1.19.4
    - Pillow 8.0.1

## 目录结构
- PicToLabel 调用字符识别模型
  - charPicToLabelWithNewModel.py 将char图片识别为对应label
  - geoPicToLabel.py  将geo图片识别为对应label
  - char_model_new 保存character字符识别模型
  - geo_model 保存geography字符识别模型
  - charPicToLabel.py 已弃用
  - char_model 已弃用

- connectModelAndFrontend 手写字符识别前后端交互
  - /src/main/java
    - Entrance.java 服务端入口
    - ServerSide.java 服务器和客户端交互
    - Java_Python_test.java 与模型的交互
    - DotToPic.java 将前端传入的点集存为图片
  - /pic
    - test.png 点集转图片文件
