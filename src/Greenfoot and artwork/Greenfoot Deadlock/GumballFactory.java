public class GumballFactory  
{
    public static Gumball generateGumball(GumballType GumballType)
    {
        switch(GumballType){
            case RED: return new RedGumball();
            case BLUE: return new BlueGumball();
            case GREEN: return new GreenGumball();
            case PURPLE: return new PurpleGumball();
            case YELLOW: return new YellowGumball();
        }
        return null;
    }
}
