package com.fun.sb.demo.cg.impl;

public class Client {

    public static void main(String[] args) {
        Client c = new Client();
        c.haveNoAuthManager();
        c.haveAuthManager();
    }

    /**
     * ģ�⣺��¼��Աû��Ȩ��
     */
    public void haveNoAuthManager() {
        System.out.println("the loginer's name is not maurice,so have no permits do manager");
        InfoManager noAuthManager = InfoManagerFactory.getAuthInstance(new AuthProxy("maurice1"));
        doCRUD(noAuthManager);
        separatorLine();
    }

    /**
     * ģ�⣺��¼��Ա��Ȩ��
     */
    public void haveAuthManager() {
        System.out.println("the loginer's name is maurice,so have permits do manager");
        InfoManager authManager = InfoManagerFactory.getAuthInstance(new AuthProxy("maurice"));
        doCRUD(authManager);
        separatorLine();
    }

    /**
     * ��Info�����ӣ����£�ɾ������ѯ����
     *
     * @param manager
     */
    private void doCRUD(InfoManager manager) {
        manager.create();
        manager.update();
        manager.delete();
        manager.query();
    }

    /**
     * ��һ���ָ��У���������
     */
    private void separatorLine() {
        System.out.println("################################");
    }

}