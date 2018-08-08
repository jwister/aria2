# aria2



配置文件的修改 
jar包修改后缀为zip或者rar 
找到文件 BOOT-INF\classes\application.yml 

注意：修改配置文件打开的时候记得用压缩工具的内部查看器打开 修改之后再保存 这样避免一系列的乱码问题

修改web端口  

server:
  port: 8855  （这里是端口修改）
aria2:
  path: /mnt/media/av
  jsonpc: http://yaaw.ijianyou.cn:6800/jsonrpc   （这里是aria2修改）
site:
  url: http://www.avtbd.com 

修改完成后改回后缀为jar
  
  
  
  
1.启用下载自动下载命令
http://ip:端口/aria2/catch

2.添加下载任务
接口：  post请求   http://ip:端口/aria2/push  
     入参
     {
    "name":"白皙性感的小美女被各种姿势爆操-绵羊般的叫声响彻房间了她最后只好用整只手了.mp4",
    "url":"http://malong.tabletennis2018.com/media/videos/mp4/33436.mp4?st=MYPCxOCPa7O38ZGjYC6cmw&e=1533714192"
    }
      

