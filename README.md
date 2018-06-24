# iFairy2
    iFairy的前端
    git地址：https://github.com/MandaJC/iFairy2
    Android sdk 版本：27
    tools版本：27.0.1
    在保持网络畅通的情况下编译
    build.gradle中glide和butterknife的版本不要更新，尤其是glide，新版不稳定，该版本为最稳定的版本
----
## 文件说明
### java文件：
#### Adapter包
    ArticleCollectAdapter：文章收藏适配器，最终没有使用，与目前显示项目不同，是另一个版本
    ColumnViewAdapter：专栏适配器（HomeFragment_3.java）
    CommentAdapter：评论适配器（）
    MainViewAdapter：主页、关注、我的文章、我的收藏的适配器（）
    MyFragmentPagerAdapter：HomeFragment中ViewPager的适配器（）
    MyPagerAdapter：文章详情中画廊的适配器（PostActivity.java）
    MyViewPager：文章详情画廊中尝试过的适配器，现弃用
#### com.mandajc.ifairy2包
    CollectActivity：我的收藏界面逻辑控制器
    HomeFragment：主页界面框架，包括关注、首页、专栏
    HomeFragment_1：关注界面逻辑控制器
    HomeFragment_2：首页界面逻辑控制器
    HomeFragment_3：专栏界面逻辑控制器
    LoginActivity：登录界面逻辑控制器
    MainActivity：整体框架，包括主页、发布、个人
    MyEssayActivity：我的文章界面逻辑控制器
    myImageView：自定义圆形图像控件
    NoMsgActivity：通知详情界面逻辑控制器
    NotificationActivity：通知界面逻辑控制器
    PersonFragment：个人中心界面逻辑控制器
    PostActivity：文章详情界面逻辑控制器
    PrivacyActivity：隐私设置界面逻辑控制器
    RegisterActivity：注册界面逻辑控制器
    ResultActivity：发布界面逻辑控制器
    SearchActivity：搜索界面框架，SearchFragment_1没做，设想是热门搜索推荐
    SearchFragent_2：搜索内容显示界面逻辑控制器
    setCenterActivity：设置中心界面逻辑控制器
    StartActivity：启动界面逻辑控制器
    TuisongActivity：推送设置界面逻辑控制器
#### model包
    Article：文章数据结构
    Collect：收藏数据结构，配合ArticleCollectAdapter使用，已弃用
    Column：专栏数据结构
    Comment：评论数据结构
    SelfInfo：个人信息数据结构
#### Util包
    GsonUtils：Gson解析框架
    HttpPath：包含所有网络请求的接口地址
    MultiPartRequest：学习使用，已弃用
    MultiPartStack：学习使用，已弃用
    MultiPartStringRequest：学习使用，已弃用
    ScreenUtil：获取实际屏幕宽高数据
#### View包
    PagerFragment：HomeFragment_1、HomeFragment_2、HomeFragment_3父控件，负责传递MainActivity传来的参数
    RoundImageView：自定义圆角图像控件，配合CardView使用
    VerticalScrollView：自定义垂直滑动控件，已弃用
#### volleyHttp包
    GsonRequest：Gson请求发送框架，学习使用，已弃用
    volleyApplication：自定义Application，替换原Application，实现了RequestQueue
----
### layout文件：
    aboutifairy：“关于小仙女”对话框界面
    actionbar：标题栏样式1
    actionbar_custom：标题栏样式2
    activity_collect：我的收藏界面，实际上与我的文章共用activity_my_essay
    activity_login：登录界面
    activity_main：整体界面
    activity_my_essay：我的文章界面
    activity_no_msg：通知详情界面
    activity_notification：通知界面
    activity_post：文章详情界面
    activity_privacy：隐私设置界面
    activity_register：注册界面
    activity_result：发布界面
    activity_search：搜索框架界面
    activity_set_center：设置中心界面
    activity_start：启动界面
    activity_tuisong：推送界面
    article_collect：弃用
    dialog：发布启动界面，弃用
    fragment_home：主页界面
    fragment_home_1：关注界面
    fragment_home_3：首页界面
    fragment_person：个人中心界面
    fragment_search__2：搜索内容界面
    main_item1：主页、关注、我的文章、我的收藏子项界面
    main_item2：评论子项界面
    main_item3：专栏子项界面
    person_collect：关注用户子项界面，已弃用
    person_name：修改昵称对话框界面
    view_one：弃用
    view_two：弃用
    view_three：弃用
