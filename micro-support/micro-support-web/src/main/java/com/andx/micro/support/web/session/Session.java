package com.andx.micro.support.web.session;

/**
 * Created by andongxu on 17-3-10.
 */
public interface Session {
    long getCreationTime();


    String getId();

    long getLastAccessedTime();

//    ServletContext getServletContext();

    void setMaxInactiveInterval(int var1);

    int getMaxInactiveInterval();

    /** @deprecated */
//    HttpSessionContext getSessionContext();

    Object getAttribute(String var1);

    /** @deprecated */
    Object getValue(String var1);

//    Enumeration<String> getAttributeNames();

    /** @deprecated */
    String[] getValueNames();

    void setAttribute(String var1, Object var2);

    /** @deprecated */
    void putValue(String var1, Object var2);

    void removeAttribute(String var1);

    /** @deprecated */
    void removeValue(String var1);

    void invalidate();

    boolean isNew();
}


