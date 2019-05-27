package yang.interfaces;

import yang.beans.MethodInfo;
import yang.beans.ServerInfo;

/**
 * rest请求调用handler
 */
public interface RestHandler {
    /**
     * 初始化服务器信息
     *
     * @param serverInfo
     */
    public void init(ServerInfo serverInfo);

    /**
     * 调用rest请求，返回接口
     *
     * @param methodInfo
     * @return
     */
    public Object invokeRest(MethodInfo methodInfo);
}
