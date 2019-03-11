package programming_languages;

public class AddC implements ArithC {
    private ArithC left;
    private ArithC right;

    public AddC(ArithC left, ArithC right) {
        this.left = left;
        this.right = right;
    }

    public ArithC getLeft() {
        return left;
    }

    public void setLeft(ArithC left) {
        this.left = left;
    }

    public ArithC getRight() {
        return right;
    }

    public void setRight(ArithC right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object obj)
    {

        // checking if both the object references are
        // referring to the same object.
        if(this == obj)
            return true;

        // it checks if the argument is of the
        // type NumC by comparing the classes
        // of the passed argument and this object.
        // if(!(obj instanceof NumC)) return false; ---> avoid.
        if(obj == null || obj.getClass()!= this.getClass())
            return false;

        // type casting of the argument.
        AddC addc = (AddC) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (addc.getLeft().equals(this.getLeft())) && (addc.getRight().equals(this.getRight())) ;
    }
}
