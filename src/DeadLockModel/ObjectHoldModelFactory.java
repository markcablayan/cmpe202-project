package DeadLockModel;

public class ObjectHoldModelFactory  
{
    public static ObjectHoldModel generateGumball(ObjectHoldType GumballType)
    {
        switch(GumballType){
            case RED: return new RedGumballModel();
            case BLUE: return new BlueGumballModel();
            case GREEN: return new GreenGumballModel();
            case PURPLE: return new PurpleGumballModel();
            case YELLOW: return new YellowGumballModel();
        }
        return null;
    }
}
