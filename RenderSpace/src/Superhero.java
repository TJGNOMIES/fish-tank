public class Superhero extends Person {
    public Superhero(int pWidth, int pHeight, int pXpos, int pYpos, int pDx, int pDy) {
        super(pWidth, pHeight, pXpos, pYpos, pDx*2, pDy*2);
    }

    @Override
    public void Shout() {
        System.out.println("HEY!!!!!!");
    }
}
