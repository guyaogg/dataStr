# 数据结构与算法
 ## 图
  ###  基本介绍
      概念
        顶点（节点
        边
        路径
        无向图
        有向图
        带权图（网
      表示多对多关系
      表示方式
        二维数组（邻接矩阵
        链表（邻接表
 ###   代码实现
      要求
      思路分析
        存储顶点 String 使用 ArrayList
        保存矩阵 int[][] edges
  ###  深度优先算法（DFS
      介绍
        从初始访问点出发
          依次访问邻接点
        访问策略是优先往纵向挖掘深入
        显然 DFS 是一个递归的过程
      步骤
        1. 访问初始节点v，并标记节点已访问
        2. 查找节点v 的第一个邻接点 w
        3. 若 w 存在，则继续执行 4 ，不存在则回到 1
          继续下一个节点 123
        4. 若 w 未被访问，对 w 进行 DFS 
          把 自己当成 v 继续123
        5. 查找节点 v 的 w 邻接点的下一个邻接点，转到步骤 3
  ###  广度优先算法（BFS
      介绍
        类似于分层搜索的过程
        需要一个队列，保存访问过的顺序
  ## 栈
   ### 逆波兰计算器
      使用后缀表达式
      中缀 -> 后缀
        初始化两个栈，运算符栈s1，中间结果栈s2
        从左至右扫描
        遇到数字，将其压入s2
        当遇到运算符时，比较s1栈顶运算符的优先级
          s1为空，或栈顶为"("直接压栈
          若优先级比栈顶运算符高压入s1
          否则将s1栈顶的运算符弹出并压入s2中，再转到上面重写比较
        如果为括号时
          为"("直接入栈s1
          ")"则依次弹出s1栈顶的运算符，并压入s2，直到遇到"("为止，此时将这一对括号丢弃
        重复扫描，压栈操作
        将s1中剩余的运算符依次弹出压入s2
        依次弹出s2的元素并输出，结果的逆序为中缀表达式的后缀表达式
  ###  完整版逆波兰计算器（有小数
 ## 递归
  ###  经典问题
      8皇后
      汉诺塔
      阶乘问题
      迷宫问题
      球和蓝子
      快排、归并排序、二分查找
   ### 规则（JVM
      执行一个方法就会创建一个栈帧
      局部变量独立，互不影响，堆中Java对象共享
      递归必须向退出递归的条件逼近，否则就会无限递归
      当方法执行完毕，或遇到return就会返回，遵守谁调用，结果返回给谁
  ###  小球迷宫
      是否能够到达终点
      思考最短路径
        策略统计起来，递归算出最小
   ### 八皇后问题
      思路
        第一个皇后先放第一行第一列
        再放第二个皇后在第二行第一列，ok继续，不OK向第三第四列尝试
        得到一个正确解后，在栈回到上一个栈，即回溯，然后得到第一行第一列所有解都拿到
        然后循环得到循环八列
 ## 排序
  ###  概念
      将一组数据，按指定顺序进行排列的过程
 ###   内部排序***
      概念
        将所有数据放入内存，在内存中完成排序
      图示算法对比
      插入
    #####    直接插入O(n^2)
          图示
          思想
            把待排序的元素看作一个有序表和一个无序表
     #####   希尔*O(nlogn)
          介绍
            又称缩小增量排序
          思想
            把记录按下标的一定增量分组
            对每组使用直接插入排序
            随着增量减少，每组包含的关键词越来越多
            当增量为1，整个文件就被分为一组，算法便终止
          图示
          实现方式
            交换法
            移动法*
              正统思想插入移动，后移
      选择
    #####    简单选择O(n^2)
          图示
          思想
            选择最小的数移到正确的位置
            一共进行数组大小-1次排序，每次排序都是一次循环
    #####    堆O(nlogn)
          介绍
            利用堆这种排序
            堆是具有以下性质的二叉树
              每个节点的值都大于或等于其左右子节点的值
                称为大顶堆
                  arr[i] >= arr[2 * i + 1] &&
                  arr[i] >= arr[2 * i + 2] 
            每个节点的值都小于或等于其左右子节点的值，称为小顶堆
              arr[i] <= arr[2 * i + 1] &&
              arr[i] <= arr[2 * i + 2] 
            大顶堆举例说明
          思想
            将待排序序列构造成一个大顶堆
            此时，整个序列的最大值就是堆顶的根节点
            将其与末尾元素进行交换，此时末尾就是最小值
            然后将剩余n - 1个元素重新构造成一个堆
              这样就能得到n个元素的次小值。
                如此反复，便能得到有序序列
          图示过程
      交换
     #####   冒泡*O(n^2)
          图示过程
          思想
            通过对待排序序列从前向后，依次进行相邻元素的值，发现逆向就交换，使大值往后
            一共进行数组的大小 -1次大的循环，每一次排序的次数逐渐减少
            如果某次交换中，并没有交换就可以直接跳出，即忧化
            时间复杂度O(n^2)
    #####    快排*O(nlogn)
          思想
            冒泡的优化
            通过较大份和较小份分割
          图示
          思路
            确定中间值，分割在递归确定
   #####   归并*
        思想
          利用归并的思想实现的排序算法
          该算法使用了经典的分治策略
          把大的问题分解成小的问题，然后递归求解
        图示
        Subtopic
   #####   基数*（桶
        介绍
          属于“分配式排序”又称“桶子法”
          通过键值的各个位的值将要排序的元素分配至某些“桶”中
          属于稳定性排序
          桶排序的扩展
        思想
          将所有待比较数值统一为同样的数位长度，
          数位长度较短的前面补零，然后从最低位开始
          依次进行一次排序，这样从低到高就拍好了
        图示
        内存消耗
          内存消耗为原数组的10倍
            且只能排序正整数字
            容易出现heap溢出
    外部排序
      概念
        数据量过大，内存不足，借助外部存储（文件，磁盘进行排序，进行合并
    概念图
##  算法的复杂度
 ###   时间
      事后统计的方法
        需要实际运行该程序
        实际运行和硬件有关
        同一台计算机相同状态下运行，才能比较那个算法速度更快
      事前估算方法
        通过分析时间复杂度来评估
      时间频度
        一个算法花费的时间与算法中语句的执行次数成成正比例，记为T(n)
        忽略常数项
        忽略低次项
        忽略系数
        即次方是唯一指标
   ###   时间复杂度j
        T(n)/f(n)极限值趋近于一个常数
          T(n)的时间复杂度就是O(f(n))
    #####    常见复杂度
          常数阶O(1)
          对数阶O(logn)
          线性阶O(n)
          线性对数阶O(nlogn)
          平方阶O(n^2)
          立方阶O(n^3)
          k次方阶O(n^k)
          指数阶O(2^n)
          n阶乘O(n!)
      最坏时间复杂度
        例图
###    空间
      存储空间的耗费估算
##  查找算法
    顺序查找
      不多说，遍历
###    二分查找
      思路
        需要查找对象为有序的
        首先确定中间的下标
        然后根据中间值和目标值比对，然后进行分布查找
        范围不断缩小
        退出条件left > right
 ###   插值查找
      介绍
        类似于二分查找
        自适应mid
        图示变化
        mid公式
          int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
        必须要写退出条件
          left > right || target < arr[0] || target > arr[arr.length - 1]
      对于二分查找的优势
        对于差值均等的查找快
        分布均匀好查找
 ###   斐波那契查找
      介绍
        又称黄金分割法查找
        使用黄金分割比例查找
          0.618
        斐波那契数列，相邻比例无限解决黄金分割
      原理
        类似全面查找
        仅仅改变mid位置
        图示
        依附于斐波那契数列
        不足就补，高位补最后一个值
 ## 哈希表
 ###   介绍
      也称散列表
      根据关键码值（Key value）直接进行访问的数据结构
      图示
      它通常把关键码值映射到表的一个位置来访问记录
      以加快查找速度，这个映射函数也叫散列函数
      存放记录的数组叫做散列表
      哈希表由来
 ###   实现思路
      哈希表数组构成
        散列函数，决定id对应那个链表
      数组链表构成
      链表数据构成
      图示
##  树结构
    原因
    树结构术语
   ### 二叉树
      概念
        每个节点最多两个子节点
        二叉树子节点分为左节点和右节点
        满二叉树
          如果所有叶子节点都在最后一层，并且节点总数为2^n - 1
          图示
        完全二叉树
          所有叶子节点都在后两层
          最后一层叶子节点在左侧连续
          倒数二层子节点右侧连续
          图示
      遍历
        前序
          先输出1.当前节点
          2.如果左子节点不为空，则递归前序遍历
          3.如果右子节点不为空，则继续前序遍历
        中序
          2
          1
          3
        后序
          3
          2
          1
      查找
        前序
          1.先查当前节点
          2.向左递归前序查找
          3.向右递归前序查找
          4.if没找到，则查找失败
        中序
          2
          1
          3
          4
        后序
          2
          3
          1
          4
      删除
        目前规定
          叶子
            删除该节点
          非叶子节点
            删除该子树
        思路
          我们应该判断子节点是否是删除节点
          如左节点不为空，且是删除节点
            就把他置为null
            且结束递归
          如果子树没有删除成功
            向子树递归删除
   ### 顺序存储二叉树
      基本说明
        数组存储方式和树的存储方式可以相互转换
        用数组存放节点，但节点遍历时使用树形方式
      特点
        顺序存储通常只考虑完全二叉树
        第n个元素的左子节点为2*n+1
        第n个元素的右子节点为2*n+2
        第n个元素的父节点为(n-1)/2
        n:表示二叉树中的第几个元素（按0开始编号
      遍历
        数组{1,2,3,4,5,6,7}
        遍历结果
          1,2,4,5,3,6,7
      功能
        实现堆排序
  ###  线索化二叉树
      问题
        将数列{1,3,6,8,10,14}构建成一颗二叉树
        中序遍历{8,3,10,1,14,6}
        如果我们希望充分的利用各个节点的左右指针，让各个节点都指向自己的前后节点，怎么办？
        解决方案：线索二叉树
      基本介绍
        n个节点的二叉链表中含义n+1（2n-(n-1) = n + 1）个空指针域
          存放指向该节点的某种遍历次序下的前驱和后继节点的指针
            这种附加的指针称为“线索
        这样加上了线索二叉链表称为线索链表
          响应称为前、中、后序线索二叉树
        一个节点的前一个节点
          前驱节点
        一个节点的后一个节点
          后继节点
      应用案例
        中序遍历结果{8,3,10,1,14,6}
      遍历
  ###  赫夫曼树
      概述
        构建二叉树，该树的带权路径长度（wpl）达到最小
          称为最优二叉树
            即赫夫曼树
        权值越大的节点离根越近
        相关概念
          路径和路径长度
            路径长度 ：终点层 - 起始层
          节点的权 及 带权路径长度
            权 ： 相当于 节点值
            权 * 路径长度
          树的带权路径长度
            所有叶子节点的 带权路径长度 的和
              WPL
          WPL 最小的就是赫夫曼树
      构建步骤
        数列，从小到大排序
        取出根节点权值最小的两颗二叉树
        组成新的二叉树
        然后根据新的二叉树，以根节点的权值再次排序
        重复上面完成构建
        图示
      功能
        实现赫夫曼编码
          解压缩
          通信领域的编码
            定长编码
              二进制字节
            变长编码
              相当于有字典集
            this
          实现过程
            通过字符重复次数变为权值
            然后构建赫夫曼树
            根据赫夫曼树编码
              左为0，右为1
              不会出现多义性
          原理
            根据排序方法不同，可能对应的编码不一样
            但是wpl是一样的，也就是不同变量总是一致
            即压缩率总是相同的
  ###  二叉排序树（BST
      介绍
        左子节点比当前节点小
        右子节点比当前节点大
      创建和遍历
      删除
        分情况讨论
          叶子节点
          单子树节点
          双子树节点
            右子树最小值替换
            或者左子树最大值替换
          根节点
  ###  AVL 树（平衡二叉树
      介绍
        可以保证查询效率较高
        特点 
          它是一颗空树
          他的左右两个子树高度差不超过 1
            左右两个子树都是一个平衡二叉树
        实现算法
          红黑树
          AVL
          替罪羊树
          Treap
          伸展树
      实现
        左旋
        右旋
        双旋转的细节
          你中有我，我中有你
  ###  多叉树（B 树
      介绍
        允许多节点成为子树，
        减少树的高度，能对二叉树进行优化
        树的度
          最大的节点度
            节点的子节点
      应用
        文件存储系统
        数据库系统
      实现
        2-3 树
          最简单的 B 树结构
          存在二、三节点
        B 树
          平衡的树结构
          每层数树结构都存放数据
        B+ 树
          B 树的变种
          只有叶子节点存放真实数据
            稠密节点
          上层为索引
            系数节点
          总共为三层
        B* 树
          B+ 树的变种
          非根和非叶子节点再增加指向兄弟节点的指针
          提高使用率
 ## 常用算法
 ###   二分查找算法
      非递归
        介绍
          只适合有序数列
 ###   分治算法
      介绍
        分而治之
        复杂问题分析成小问题
      步骤
        分解
        解决
        合并
      汉诺塔
        如果一个盘 ，A - C
        n >= 2
          最上面的盘 A -> B
          最下面的盘 A -> C
          把 B 塔的所有盘 B -> C
 ###   动态规划算法
      介绍
        大问题分校问题
        类似分治算法
        不同的是，适用于动态规划的问题求解
          子问题往往不是独立的，通常与上一级解有关联
        可以通过填表方式，逐步得到最优解
      背包问题
        01 背包
          v[i][0] = v[0][i] = 0
          当 w[i] > j 时：v[i][j] = v[i - 1][j]
          当 j >= w[i] ;v[i][j] = Math.max(v[i - 1][j],v[i -1][j - w[i] + v[i]
        无限背包
          可以转换为 01 背包
   ### KMP 算法
      字符串匹配问题
      暴力匹配算法
        Subtopic
      KMP 
        解决模式串在文本串是否出现过
        利用之前判断过的信息，通过一个next()数组
          部分匹配表
            前缀和后缀最长匹配长度
  ###  贪心算法
      介绍
        在每一步求解过程中，每次都使用最好，最优结果
        贪心算法不一定是最优解
          但是都是相对接近最优解
      集合覆盖问题
 ###   普里姆算法
      最小生成树
        每一步拿到达过的节点去链接未连接的节点，每次取值最优的
        BFS + 贪心
      修路问题
        各村庄都能联通
        总里程数最小
  ###  克鲁斯卡尔算法
      同上处理最小生成树
        按照权值从小到大的顺序选择 n - 1 条边 
          并保证 n - 1 条边不构成回路
      公交站问题
   ### 迪杰斯特拉算法
      介绍
        BFS 应用
      根据某顶点生成最小生成树
  ###  弗洛伊德算法
      同上使用，不过一次求出所有最短路径
      介绍
        各个顶点之间的最短路径
        从出发节点 通过中间节点 到达 终点
          三次 for 循环 求得最小路径
   ### 马踏棋盘算法
