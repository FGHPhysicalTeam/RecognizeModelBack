# recognizeModelBackend
- charPicToLabel 调用字符识别模型
  - charPicToLabel.py 将图片识别为对应label
    - 需要修改模型和图片的绝对路径

- connectModelAndFrontend 手写字符识别前后端交互
  - ServerSide.java 服务器端入口
  - Java_Python_test 在java中调用charPicToLabel.py
    - 需要修改charPicToLabel.py的绝对路径
  - DotToPic.java 将前端传入的点集存为图片
    - 需要修改文件保存路径为本地路径
