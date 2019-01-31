public class SocketModel{
    /// <summary>
    /// 一级协议 用于区分所属模块
    /// </summary>
    public byte type;
    /// <summary>
    /// 二级协议 用于区分 模块下所属子模块
    /// </summary>
    public int area;
    /// <summary>
    /// 三级协议 用于区分当前处理的逻辑功能
    /// </summary>
    public int command;
    /// <summary>
    /// 消息体 当前需要处理的主体数据
    /// </summary>
    public Object message;
}