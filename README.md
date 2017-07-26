# codebuild
代码生成工具

## 快速开始
下载配置文件：/src/main/resources/buildConfig.zip
配置文件结构：
````
|__template
|   |__dao.vm
|   |__model.vm
|__config.xml
|__tables.xml
````
### config.xml
包含数据库配置，常量配置，编码配置等，可在文件内注释

### tables.xml
表生成配置，一个task为生成一个文件，每个task的详细配置见文件内注释

### template 模版
模版预发同velocity，可用变量如下：
#### 全局变量
serialVersionUID 随机序列号<br>
date  当前时间，为 java.util.Date类型<br>
dateUtil 日期工具类 见org.apache.commons.lang.time.DateFormatUtils.DateFormatUtils<br>
creator  代码作者，见下文入口启动类中的参数

#### 表相关变量
tableClassName 表转类名，无任何后缀
lowerTableClassName 小写表类名
table  类<br>
|__name  表名<br>
|__desc  表备注<br>
|__className 驼峰类名<br>
|__columns  list列<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__name 列名<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__comment 列备注<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__isPrimaryKey 是否主键<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__dbType 数据库类型<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__jdbcType jdbc类型<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__javaType java类型<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   |__javaClass java类型class名称例：java.lang.String<br>


#### 当前任务配置变量，主要是tables.xml配置后的属性,以person表默认配置为例
packageName 包名<br>
className   大写类名 PersonDO<br>
lowerClassName 小写类名 personDO<br>


#### 任务变量  获取其他模块的变量，如dao中获取model的文件名
task任务名 如 model<br>
|__className  类名<br>
|__packageName 包名<br>
|__lowerClassName 小写类名<br>

#### 入口函数
org.erik.code.main.Geneter
