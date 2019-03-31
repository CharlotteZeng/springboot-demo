package com.springbootdemo.test;

/**
 * 设配器设计模式
 * (1)目标(Target)角色：这就是所期待得到的接口。注意：由于这里讨论的是类适配器模式，因此目标不可以是类。

 (2)源(Adapee)角色：现在需要适配的接口。

 (3)适配器(Adaper)角色：适配器类是本模式的核心。适配器把源接口转换成目标接口。显然，这一角色不可以是接口，而必须是具体类。
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