# widdo

[![](https://img.shields.io/github/languages/code-size/OnlyXYL/widdo)]()

[![](https://img.shields.io/github/commit-activity/w/OnlyXYL/widdo)](https://github.com/OnlyXYL)

[![](https://img.shields.io/github/commit-status/OnlyXYL/widdo/263.1.2.x/f8bf0bf)]()

[![](https://img.shields.io/github/v/release/OnlyXYl/widdo?display_name=tag&include_prereleases)]()

[![](https://img.shields.io/github/v/tag/OnlyXYL/widdo)]() 

[![](https://img.shields.io/bitbucket/issues/OnlyXYL/widdo)]()

[![](https://img.shields.io/github/issues-closed/OnlyXYL/widdo)]()

#### [0. 项目背景]()
###### 个人网站。用来记录学习，工作，生活相关的信息

#### [1. 项目结构]()

```
widdo
│
├─widdo-autoconfigure                           ------ 自动配置模块
├─widdo-cloud                                   ------ 生活模块
│   ├─widdo-cloud-gateway                       ------ 网关模块（9900）
├─widdo-packages                                ------ 公共包模块
│   ├─widdo-assistant                           ------ 辅助包
│   ├─widdo-cache(@Deprecated)                  ------ 缓存包(后面移入autoconfigure)
│   ├─widdo-data(@Deprecated)                   ------ 数据包(后面移入autoconfigure)
├─widdo-services                                ------ 服务模块
│   ├─widdo-life                                ------ 生活服务（9901）
│   ├─widdo-study                               ------ 学习服务（9902）
└─widdo-starters                                ------ 引用模块
    ├─widdo-starter-neo4j                       ------ neo4j模块
    ├─widdo-starter-orientdb                    ------ orientdb模块

```
---

#### [2. 项目介绍]()

widdo 是一个名称有内涵（^_^）。目前，用来记录日常的工作，学习，生活中的点点滴滴。同时，会记录对应的操作日志，用来收集信息。
同时，还会提供图谱可视化，用来直观的看关系。目前提供一下功能：

* ***java动态编译：***

#### [3. 版本说明]()
工程以springboot版本为基础，加上主版本，次版本，补丁版本号。当前springboot版本为2.6.3。版本号格式为：263.1.1.0

  - 修复bug，但不影响API，增长补丁版本号
 
  - API保持向下兼容的增加/修改时，增长次版本号
  
  - 进行向下的不兼容修改时，增长主版本号
  
另外，高版本次升级，低版本次需要清零。



#### [4. 效果演示]()
#### [5. 项目特点]()
#### [6. 基本架构]()
#### [7. 集成方式]()
#### [8. 使用方法]()
#### [9. 关于作者]()
#### [10. 鸣谢]()
