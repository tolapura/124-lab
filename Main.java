package programming_languages;

import de.tudresden.inf.lat.jsexp.Sexp;
import de.tudresden.inf.lat.jsexp.SexpFactory;
import de.tudresden.inf.lat.jsexp.SexpParserException;

public class Main {

    /* tests */
    public static void parserTest() throws SexpParserException {
        System.out.println(parser(SexpFactory.parse("4")).equals(new NumC(4)));
        System.out.println(parser(SexpFactory.parse("(* 7 2)")).equals(new MulC(new NumC(7), new NumC(2))));
        System.out.println(parser(SexpFactory.parse("(+ 7 2)")).equals(new AddC(new NumC(7), new NumC(2))));
        System.out.println(parser(SexpFactory.parse("(* (+ 7 2) 3)")).equals(new MulC(new AddC(new NumC(7), new NumC(2)), new NumC(3))));
        System.out.println(parser(SexpFactory.parse("(+ (* 7 2) 3)")).equals(new AddC(new MulC(new NumC(7), new NumC(2)), new NumC(3))));
        System.out.println(parser(SexpFactory.parse("(+ 3 (* 7 2))")).equals(new AddC(new NumC(3), new MulC(new NumC(7), new NumC(2)))));
        System.out.println(parser(SexpFactory.parse("(* 3 (+ 7 2))")).equals(new MulC(new NumC(3), new AddC(new NumC(7), new NumC(2)))));
    }

    public static void interpTest() throws SexpParserException {
        System.out.println(interp(parser(SexpFactory.parse("4"))) == 4);
        System.out.println(interp(parser(SexpFactory.parse("(* 2 7)"))) == 14);
        System.out.println(interp(parser(SexpFactory.parse("(+ 2 7)"))) == 9);
        System.out.println(interp(parser(SexpFactory.parse("(+ (* 2 3) 7)"))) == 13);
        System.out.println(interp(parser(SexpFactory.parse("(* (+ 2 3) 7)"))) == 35);
        System.out.println(interp(parser(SexpFactory.parse("(* 2 (+ 3 8))"))) == 22);
        System.out.println(interp(parser(SexpFactory.parse("(+ 2 (* 3 8))"))) == 26);
        System.out.println(interp(parser(SexpFactory.parse("(+ (+ 2 3) (+ 3 1))"))) == 9);
        System.out.println(interp(parser(SexpFactory.parse("(* (+ 2 3) (+ 3 1))"))) == 20);
    }
    /* end test */

    public static ArithC parser (Sexp s) {
        if(s.isAtomic()) {
            return new NumC(Integer.parseInt(s.toString()));
        }
        else if (s.get(0).toString().equals("+")) {
            return new AddC(parser(s.get(1)), parser(s.get(2)));
        }
        else if (s.get(0).toString().equals("*")) {
            return new MulC(parser(s.get(1)), parser(s.get(2)));
        }
        return null;
    }

    public static int interp(ArithC arithC) {
            if (arithC instanceof NumC) {
                return ((NumC) arithC).getValue();
            } else if (arithC instanceof MulC) {
                return (interp(((MulC) arithC).getLeft()) * interp(((MulC) arithC).getRight()));
            } else if (arithC instanceof AddC) {
                return (interp(((AddC) arithC).getLeft()) + interp(((AddC) arithC).getRight()));
            }
        return 0;
    }

    public static void main(String[] args) {
        try {
            parserTest();
            interpTest();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
