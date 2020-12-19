# recognizeModelBackend
***前端调用后端模型，只需clone这个仓库***
## 起步
- 修改charPicToLabel.py和geoPicToLabel.py的SAVER_DIR和path
- 修改GlobalVariable类中的pythonAddress, charPicToLabel, geoPicToLabel和picAddress
- Entrance类为服务器入口，运行main启动服务器

## 目录结构
- PicToLabel 调用字符识别模型
  - charPicToLabel.py 将char图片识别为对应label
  - geoPicToLabel.py  将geo图片识别为对应label
  - char_model 保存character字符识别模型
  - geo_model 保存geography字符识别模型

- connectModelAndFrontend 手写字符识别前后端交互
  - /src/main/java
    - Entrance.java 服务端入口
    - ServerSide.java 服务器和客户端交互
    - Java_Python_test.java 与模型的交互
    - DotToPic.java 将前端传入的点集存为图片
  - /pic
    - test.png 点集转图片文件
