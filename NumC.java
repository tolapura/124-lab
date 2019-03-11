package programming_languages;

public class NumC implements ArithC {
    private int value;

    public NumC(int num) {
        this.value = num;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
        NumC numc = (NumC) obj;

        // comparing the state of argument with
        // the state of 'this' Object.
        return (numc.value == this.value);
    }
}
