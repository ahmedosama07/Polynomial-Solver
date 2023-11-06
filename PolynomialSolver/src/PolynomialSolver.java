import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, Object element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(Object element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public Object get(int index);
/**
 * 
 * @param index
 * @return the node at the specified position in this list.
 */
public SLNode getNode(int index);
/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, Object element);
/**
* Removes all of the elements from this list.
*/
public void clear();
/**
* @return true if this list contains no elements.
*/
public boolean isEmpty();
/**
* Removes the element at the specified position in this list.
* @param index
*/
public void remove(int index);
/**
* @return the number of elements in this list.
*/
public int size();
/**
* @param fromIndex
* @param toIndex
* @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
*/
public ILinkedList sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(Object o);
}

interface IPolynomialSolver {
    /**
    * Set polynomial terms (coefficients & exponents)
    * @param poly: name of the polynomial
    * @param terms: array of [coefficients][exponents]
    */
    void setPolynomial(char poly, int[][] terms);
  
    /**
    * Print the polynomial in ordered human readable representation
    * @param poly: name of the polynomial
    * @return: polynomial in the form like 27x^2+x-1
    */
    String print(char poly);
  
    /**
    * Clear the polynomial
    * @param poly: name of the polynomial
    */
      void clearPolynomial(char poly);
  
    /**
    * Evaluate the polynomial
    * @param poly: name of the polynomial
    * @param value: the polynomial constant value
    * @return the value of the polynomial
    */
    float evaluatePolynomial(char poly, float value);
  
    /**
    * Add two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial
    */
    int[][] add(char poly1, char poly2);
  
    /**
    * Subtract two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);
  
    /**
    * Multiply two polynomials
    * @param poly1: first polynomial
    * @param poly2: second polynomial
    * @return: the result polynomial
    */
    int[][] multiply(char poly1, char poly2);
}

class SLNode {
    private Object element;
    private SLNode next;

    /**
     * Node constructor
     * @param e
     * @param n
     */
    public SLNode(Object e, SLNode n)
    {
        this.element = e;
        this.next = n;
    }

    /* Getters */
    /**
     * gets node data
     * @return node data
     */
    public Object getElement()
    {
        return element;
    }
    /**
     * gets next node
     * @return next node
     */
    public SLNode getNext()
    {
        return next;
    }

    /* Setters */
    /**
     * sets node's data
     * @param newElement
     */
    public void setElement(Object newElement)
    {
        this.element = newElement;
    }
    /**
     * sets node's next node
     * @param newNext
     */
    public void setNext(SLNode newNext)
    {
        this.next = newNext;
    }
}

class SingleLinkedList implements ILinkedList {
    private SLNode head;

    SingleLinkedList() {
        this.head = null;
    }

    public void add(int index, Object element)
    {
        if (index < 0 || index > size())
        {
            System.out.println("Error");
            return;
        }

        SLNode newNode = new SLNode(element, null);
        if (index == 0)
        {
            newNode.setNext(head);
            head = newNode;
        }
        else
        {
            SLNode currNode = head;
            for (int i = 0; i < index - 1; ++i)
            {
                currNode = currNode.getNext();
            }
            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);
        }
    }
    
    public void add(Object element)
    {
        SLNode newNode = new SLNode(element, null);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            SLNode currNode = head;
            while (currNode.getNext() != null)
            {
                currNode = currNode.getNext();
            }
            currNode.setNext(newNode);
        }
    }
    
    public Object get(int index)
    {
        if (index < 0 || index >= size())
        {
            System.out.println("Error");
            return null;
        }
        SLNode currNode = head;
        for (int i = 0; i < index; ++i)
        {
            currNode = currNode.getNext();
        }
        return currNode.getElement();
    }

    public SLNode getNode(int index)
    {
        if (index < 0 || index >= size())
        {
            return null;
        }
        SLNode currNode = head;
        for (int i = 0; i < index; ++i)
        {
            currNode = currNode.getNext();
        }
        return currNode;
    }

    public void set(int index, Object element)
    {
        if (index < 0 || index >= size())
        {
            System.out.println("Error");
            return;
        }

        SLNode currNode = head;
        for( int i = 0; i < index; ++i)
        {
            currNode = currNode.getNext();
        }
        currNode.setElement(element);
    }
    
    public void clear()
    {
        head = null;
    }
    
    public boolean isEmpty()
    {
        return head == null;
    }
    
    public void remove(int index)
    {
        if (index < 0 || index >= size())
        {
            System.out.println("Error");
            return;
        }
        if (index == 0)
        {
            head = head.getNext();
        }
        else
        {
            SLNode currNode = head;
            for (int i = 0; i < index - 1; i++) {
                currNode = currNode.getNext();
            }
            currNode.setNext(currNode.getNext().getNext());
        }
    }
    
    public int size()
    {
        int length = 0;
        SLNode currNode = head;
        while (currNode != null) {
            length++;
            currNode = currNode.getNext();
        }
        return length;
    }
    
    public SingleLinkedList sublist(int fromIndex, int toIndex)
    {
        if (fromIndex < 0 || fromIndex >= size() || toIndex < 0 || toIndex >= size() || fromIndex > toIndex)
        {
            System.out.println("Error");
            return null;
        }
        SingleLinkedList subList = new SingleLinkedList();
        SLNode currNode = head;
        for (int i = 0; i <= toIndex; ++i)
        {
            if (i >= fromIndex)
            {
                subList.add(currNode.getElement());
            }
            currNode = currNode.getNext();
        }

        return subList;
    }
    
    public boolean contains(Object o)
    {
        SLNode currNode = head;
        while (currNode != null)
        {
            if (currNode.getElement() == o)
            {
                return true;
            }
            currNode = currNode.getNext();
        }
        return false;
    }

    public void printList()
    {
        System.out.print("[");
        SLNode currNode = head;
        // if (head == null) return;
        for(int i = 0; i < size(); ++i) {
            System.out.print(currNode.getElement());
            currNode = currNode.getNext();
            if(i != size() - 1)
                System.out.print(", ");
        }
        System.out.println("]");
    }
}


class  term {
    private int coefficient;
    private int exponent;

    term (int c, int e)
    {
        this.coefficient = c;
        this.exponent = e;
    }

    /* Getters */
    /**
     * gets term coefficient
     * @return term coefficient
     */
    public int getCoefficient()
    {
        return this.coefficient;
    }
    /**
     * gets term exponent
     * @return term exponent
     */
    public int getExponent()
    {
        return this.exponent;
    }

    /* Setters */
    /**
     * sets term coefficient
     * @param newCoefficient
     */
    public void setElement(int newCoefficient)
    {
        this.coefficient = newCoefficient;
    }
    /**
     * sets term exponent
     * @param newExponent
     */
    public void setNext(int newExponent)
    {
        this.exponent = newExponent;
    }
}

public class PolynomialSolver implements IPolynomialSolver{
    SingleLinkedList polynomialA;
    SingleLinkedList polynomialB;
    SingleLinkedList polynomialC;
    SingleLinkedList polynomialR;

    PolynomialSolver()
    {
        polynomialA = new SingleLinkedList();
        polynomialB = new SingleLinkedList();
        polynomialC = new SingleLinkedList();
        polynomialR = new SingleLinkedList();
    }

    private SingleLinkedList selectPolynomial(char poly)
    {
        SingleLinkedList polynomial = null;
        switch (poly) {
            case 'A':
                polynomial = polynomialA;
                break;
            case 'B':
                polynomial = polynomialB;
                break;
            case 'C':
                polynomial = polynomialC;
                break;
        
            default:
                // System.out.println("Error");
                break;
        }
        return polynomial;
    }

    private String printResult()
    {
        SingleLinkedList polynomial = polynomialR;
        StringBuilder result = new StringBuilder();
        term t;
        if (polynomial == null || polynomial.isEmpty())
        {
            return "Error";
        }
        else
        {
            for (int i = 0; i < polynomial.size(); ++i)
            {
                t = (term)polynomial.getNode(i).getElement();
                int coefficient = t.getCoefficient();
                int exponent = t.getExponent();
                if (i > 0 && coefficient > 0)
                {
                    result.append("+");
                }

                if (coefficient != 0)
                {
                    if (coefficient != 1 && coefficient != - 1)
                    {
                        result.append(coefficient);
                    }
                    else if (coefficient == -1)
                    {
                        result.append("-");
                    }

                    if(exponent != 0)
                    {
                        result.append("x");
                        if (exponent != 1)
                        {
                            result.append("^").append(exponent);
                        }
                    }
                    else if(exponent == 0 && coefficient == 1)
                    {
                        result.append(coefficient);
                    }
                }
            }
        }
        if (result.length() == 0)
        {
            result.append("0");
        }
        return result.toString();
    }

    private int[][] toTerms(SingleLinkedList polynomial)
    {
        int[][] terms = new int[polynomial.size()][2];
        try {
            term t;
            int i = 0;
            while (i < polynomial.size()) {
                t = (term) polynomial.getNode(i).getElement();
                terms[i][0] = t.getCoefficient();
                terms[i][1] = t.getExponent();
                ++i;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return terms;
    }

    public void setPolynomial(char poly, int[][] terms)
    {
        SingleLinkedList polynomial = selectPolynomial(poly);
        try {
            polynomial.clear();
            for (int[] tData : terms)
            {
                term t = new term(tData[0], tData[1]);
                polynomial.add(t);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public String print(char poly)
    {
        SingleLinkedList polynomial;
        if (poly == 'R') polynomial = polynomialR;
        else polynomial = selectPolynomial(poly);
        StringBuilder result = new StringBuilder();
        term t;
        if (polynomial == null || polynomial.isEmpty())
        {
            return "Error";
        }
        else
        {
            for (int i = 0; i < polynomial.size(); ++i)
            {
                t = (term)polynomial.getNode(i).getElement();
                int coefficient = t.getCoefficient();
                int exponent = t.getExponent();
                if (i > 0 && coefficient > 0)
                {
                    result.append("+");
                }

                if (coefficient != 0)
                {
                    if (coefficient != 1 && coefficient != - 1)
                    {
                        result.append(coefficient);
                    }
                    else if (coefficient == -1)
                    {
                        result.append("-");
                    }

                    if(exponent != 0)
                    {
                        result.append("x");
                        if (exponent != 1)
                        {
                            result.append("^").append(exponent);
                        }
                    }
                    else if(exponent == 0 && coefficient == 1)
                    {
                        result.append(coefficient);
                    }
                }
            }
        }
        if (result.length() == 0)
        {
            result.append("0");
        }
        return result.toString();
    }

    public void clearPolynomial(char poly)
    {
        SingleLinkedList polynomial = selectPolynomial(poly);
        if (polynomial.isEmpty())
        {
            System.out.println("Error");
            return;
        }
        polynomial.clear();
        polynomial.printList();
    }

    public float evaluatePolynomial(char poly, float value)
    {
        SingleLinkedList polynomial = selectPolynomial(poly);
        if (polynomial == null || polynomial.isEmpty())
        {
            return Float.NaN;
        }
        term t;
        float result = 0;
        for (int i = 0; i < polynomial.size(); ++i)
        {
            t = (term)polynomial.getNode(i).getElement();
            int coefficient = t.getCoefficient();
            int exponent = t.getExponent();
            result += coefficient * Math.pow(value, exponent);
        }
        return result;
    }

    public int[][] add(char poly1, char poly2)
    {
        SingleLinkedList polynomial1 = selectPolynomial(poly1);
        SingleLinkedList polynomial2 = selectPolynomial(poly2);
        try {
            int i = 0;
            int j = 0;
            term t1;
            term t2;
            while (i < polynomial1.size() && j < polynomial2.size())
            {
                t1 = (term)polynomial1.getNode(i).getElement();
                t2 = (term)polynomial2.getNode(j).getElement();

                if (t1.getExponent() == t2.getExponent())
                {
                    polynomialR.add(new term(t1.getCoefficient() + t2.getCoefficient(), t1.getExponent()));
                    ++i;
                    ++j;
                }
                else if (t1.getExponent() > t2.getExponent())
                {
                    polynomialR.add(new term(t1.getCoefficient(), t1.getExponent()));
                    ++i;
                }
                else
                {
                    polynomialR.add(new term(t2.getCoefficient(), t2.getExponent()));
                    ++j;
                }
                //System.out.println(polynomial1.getNode(i));
                //System.out.println(polynomial2.getNode(j));
                if (polynomial1.getNode(i) == null)
                {
                    while (polynomial2.getNode(j) != null)
                    {
                        t2 = (term)polynomial2.getNode(j).getElement();
                        polynomialR.add(new term(t2.getCoefficient(), t2.getExponent()));
                        ++j;
                    }
                }
                if (polynomial2.getNode(j) == null)
                {
                    while (polynomial1.getNode(i) != null)
                    {
                        t1 = (term)polynomial1.getNode(i).getElement();
                        polynomialR.add(new term(t1.getCoefficient(), t1.getExponent()));
                        ++i;
                    }
                }
            }
            System.out.println(printResult());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error");
        }
        return toTerms(polynomialR);
    }

    public int[][] subtract(char poly1, char poly2)
    {
        SingleLinkedList polynomial1 = selectPolynomial(poly1);
        SingleLinkedList polynomial2 = selectPolynomial(poly2);
        try {
            int i = 0;
            int j = 0;
            term t1;
            term t2;
            while (i < polynomial1.size() && j < polynomial2.size())
            {
                t1 = (term)polynomial1.getNode(i).getElement();
                t2 = (term)polynomial2.getNode(j).getElement();

                if (t1.getExponent() == t2.getExponent())
                {
                    polynomialR.add(new term(t1.getCoefficient() - t2.getCoefficient(), t1.getExponent()));
                    ++i;
                    ++j;
                }
                else if (t1.getExponent() > t2.getExponent())
                {
                    polynomialR.add(new term(t1.getCoefficient(), t1.getExponent()));
                    ++i;
                }
                else
                {
                    polynomialR.add(new term(-t2.getCoefficient(), t2.getExponent()));
                    ++j;
                }

                if (polynomial1.getNode(i) == null)
                {
                    while (polynomial2.getNode(j) != null)
                    {
                        t2 = (term)polynomial2.getNode(j).getElement();
                        polynomialR.add(new term(-t2.getCoefficient(), t2.getExponent()));
                        ++j;
                    }
                }
                if (polynomial2.getNode(j) == null)
                {
                    while (polynomial1.getNode(i) != null)
                    {
                        t1 = (term)polynomial1.getNode(i).getElement();
                        polynomialR.add(new term(t1.getCoefficient(), t1.getExponent()));
                        ++i;
                    }
                }
            }
            System.out.println(printResult());
        } catch (Exception e) {
            System.err.println("Error");
        }
        return toTerms(polynomialR);
    }

    public int[][] multiply(char poly1, char poly2)
    {
        SingleLinkedList polynomial1 = selectPolynomial(poly1);
        SingleLinkedList polynomial2 = selectPolynomial(poly2);
        term t1;
        term t2;
        term temp;
        int i = 0;
        int j = 0;
        int maxExponent = 0;
        int k = 0;
        try {
            if(!polynomial1.isEmpty() && !polynomial2.isEmpty())
            {
                t1 = (term) polynomial1.getNode(i).getElement();
                t2 = (term) polynomial2.getNode(j).getElement();
                maxExponent = t1.getExponent() + t2.getExponent();
            }
            else
            {
                System.out.println("Error");
                System.exit(0);
            }

            for (k = 0; k <= maxExponent; ++k)
            {
                polynomialR.add(new term(0, maxExponent - k));
            }
            k = 0;
            while (k < polynomialR.size()) {
                temp = (term) polynomialR.getNode(k).getElement();
                int coeff = 0;
                while (i < polynomial1.size()) {
                    t1 = (term) polynomial1.getNode(i).getElement();
                    while (j < polynomial2.size()) {
                        t2 = (term) polynomial2.getNode(j).getElement();
                        if (temp.getExponent() == t1.getExponent() + t2.getExponent())
                        {
                            coeff += t1.getCoefficient() * t2.getCoefficient();
                        }
                        ++j;
                    }
                    j = 0;
                    ++i;
                }
                i = 0;
                polynomialR.set(k, new term(coeff, temp.getExponent()));
                ++k;
            }
            System.out.println(printResult());
        } catch (Exception e) {
            System.err.println("Error");
        }
        return toTerms(polynomialR);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PolynomialSolver PS = new PolynomialSolver();

            while(sc.hasNextLine())
            {
                String op = sc.nextLine();
                char poly1;
                char poly2;
                Object r;
                // System.out.println(op);
                switch (op)
                {
                    case "set":
                        poly1 = sc.nextLine().charAt(0);
                        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
                        String[] s = sin.split(",");
                        int[] arr = new int[s.length];
                        if (!(s.length == 1 && s[0].isEmpty()))
                        {
                            for(int i = 0; i < s.length; ++i)
                            {
                                try {
                                    //list.add(Integer.parseInt(s[i]));
                                    arr[i] = Integer.parseInt(s[i]);
                                } catch (Exception e) {
                                    System.out.println("Error");
                                    return;
                                }
                            }
                            int[][] polynomial = new int[arr.length][2];
                            for(int i = 0; i < arr.length; ++i)
                            {
                                polynomial[i][0] = arr[i];
                                polynomial[i][1] = arr.length - i - 1;
                            }
                            PS.setPolynomial(poly1, polynomial);
                        }
                        else
                        {
                            PS.setPolynomial(poly1, null);
                        }
                        break;
                    case "print":
                        poly1 = sc.nextLine().charAt(0);
                        System.out.println(PS.print(poly1));
                        break;
                    case "add":
                        poly1 = sc.nextLine().charAt(0);
                        poly2 = sc.nextLine().charAt(0);
                        r = PS.add(poly1, poly2);
                        break;
                    case "sub":
                        poly1 = sc.nextLine().charAt(0);
                        poly2 = sc.nextLine().charAt(0);
                        r = PS.subtract(poly1, poly2);
                        break;
                    case "mult":
                        poly1 = sc.nextLine().charAt(0);
                        poly2 = sc.nextLine().charAt(0);
                        r = PS.multiply(poly1, poly2);
                        break;
                    case "clear":
                        poly1 = sc.nextLine().charAt(0);
                        PS.clearPolynomial(poly1);
                        break;
                    case "eval":
                        poly1 = sc.nextLine().charAt(0);
                        float val = Float.parseFloat(sc.nextLine());
                        float result = PS.evaluatePolynomial(poly1, val);
                        if(Float.isNaN(result)) break;
                        if(result == (long) result)
                            System.out.println(String.format("%d",(long)result));
                        else
                            System.out.println(String.format("%s",result));
                        break;
                    default:
                        System.out.println("Error");
                        break;
                }
            }
            sc.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}