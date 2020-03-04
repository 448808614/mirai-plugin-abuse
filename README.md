# mirai-plugin-abuse
Mirai口吐芬芳插件

## 使用说明

+ 包含关键词时，会口吐芬芳
+ 关键词配合At对指定人口吐芬芳
+ 可按群配置开关状态（默认关闭）
    * 发送：口吐芬芳开
    * 发送：口吐芬芳关
+ 私聊默认全开启
+ 配置管理员账户控制状态

## 配置文件

目录位于(不存在则自行创建)：`/plugins/Abuse/setting.yml`

```yaml
owner: 123456789 # 管理员账号
level: min    # 芬芳等级，min、max
keyword: [骂] # 关键词，多个关键词用逗号分割
whilteList: [] # 初始配置开启状态的群号，多个用逗号分割
```

## 说明

口吐芬芳语料来自[NMSL](https://nmsl.shadiao.app/)提供的API
