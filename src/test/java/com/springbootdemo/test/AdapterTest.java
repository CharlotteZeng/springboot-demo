package com.springbootdemo.test;

/**
 * 设配器设计模式
 */
public class AdapterTest {
    public static void main(String[] args){
        UniversalCharger universalCharger = new UniversalCharger();
        universalCharger.phoneCharger();

        UniversalCharger1 universalCharger1 = new UniversalCharger1(new IphoneCharger());
        universalCharger1.phoneCharger();
    }
}
class IphoneCharger{
    public void appleCharger(){
        System.out.println("苹果手机开始充电");
    }
}
interface ICharger{
    public void phoneCharger();
}

/**
 * 类适配示例
 */
class UniversalCharger extends IphoneCharger implements ICharger{

    @Override
    public void phoneCharger() {
        System.out.println("万能充电器开始充电，");
        super.appleCharger();
    }
}

/**
 * 对象适配示例
 */
class UniversalCharger1  implements ICharger{
    IphoneCharger iphoneCharger;

    public UniversalCharger1(IphoneCharger iphoneCharger) {
        this.iphoneCharger = iphoneCharger;
    }

    @Override
    public void phoneCharger() {
        System.out.println("万能充电器开始充电，");
        iphoneCharger.appleCharger();
    }
}