import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class SLNode<T> {
    private T element;
    private SLNode<T> next;

    /**
     * Node constructor
     * @param e
     * @param n
     */
    public SLNode(T e, SLNode<T> n)
    {
        element = e;
        next = n;
    }

    /* Getters */
    /**
     * gets node data
     * @return node data
     */
    public T getElement()
    {
        return element;
    }
    /**
     * gets next node
     * @return next node
     */
    public SLNode<T> getNext()
    {
        return next;
    }

    /* Setters */
    /**
     * sets node's data
     * @param newElement
     */
    public void setElement(T newElement)
    {
        element = newElement;
    }
    /**
     * sets node's next node
     * @param newNext
     */
    public void setNext(SLNode<T> newNext)
    {
        next = newNext;
    }
}

interface ILinkedList<T> {
/**
* Inserts a specified element at the specified position in the list.
* @param index
* @param element
*/
public void add(int index, T element);
/**
* Inserts the specified element at the end of the list.
* @param element
*/
public void add(T element);
/**
* @param index
* @return the element at the specified position in this list.
*/
public T get(int index);

/**
* Replaces the element at the specified position in this list with the
* specified element.
* @param index
* @param element
*/
public void set(int index, T element);
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
public ILinkedList<T> sublist(int fromIndex, int toIndex);
/**
* @param o
* @return true if this list contains an element with the same value as the specified element.
*/
public boolean contains(T element);
}

class SingleLinkedList<T> implements ILinkedList<T> {
    private SLNode<T> head;

    SingleLinkedList() {
        this.head = null;
    }

    public void add(int index, T element)
    {
        if (index < 0 || index > size())
        {
            System.out.println("Error");
            return;
        }

        SLNode<T> newNode = new SLNode<>(element, null);
        if (index == 0)
        {
            newNode.setNext(head);
            head = newNode;
        }
        else
        {
            SLNode<T> currNode = head;
            for (int i = 0; i < index - 1; ++i)
            {
                currNode = currNode.getNext();
            }
            newNode.setNext(currNode.getNext());
            currNode.setNext(newNode);
        }
    }
    
    public void add(T element)
    {
        SLNode<T> newNode = new SLNode<>(element, null);
        if(head == null)
        {
            head = newNode;
        }
        else
        {
            SLNode<T> currNode = head;
            while (currNode.getNext() != null)
            {
                currNode = currNode.getNext();
            }
            currNode.setNext(newNode);
        }
    }
    
    public T get(int index)
    {
        if (index < 0 || index >= size())
        {
            // System.out.println("Error");
            return null;
        }
        SLNode<T> currNode = head;
        for (int i = 0; i < index; ++i)
        {
            currNode = currNode.getNext();
        }
        return currNode.getElement();
    }

    public void set(int index, T element)
    {
        if (index < 0 || index >= size())
        {
            // System.out.println("Error");
            return;
        }

        SLNode<T> currNode = head;
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
            // System.out.println("Error");
            return;
        }
        if (index == 0)
        {
            head = head.getNext();
        }
        else
        {
            SLNode<T> currNode = head;
            for (int i = 0; i < index - 1; ++i) {
                currNode = currNode.getNext();
            }
            currNode.setNext(currNode.getNext().getNext());
        }
    }
    
    public int size()
    {
        int length = 0;
        SLNode<T> currNode = head;
        while (currNode != null) {
            length++;
            currNode = currNode.getNext();
        }
        return length;
    }
    
    public SingleLinkedList<T> sublist(int fromIndex, int toIndex)
    {
        if (fromIndex < 0 || fromIndex >= size() || toIndex < 0 || toIndex >= size() || fromIndex > toIndex)
        {
            // System.out.println("Error");
            return null;
        }
        SingleLinkedList<T> subList = new SingleLinkedList<>();
        SLNode<T> currNode = head;
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
    
    public boolean contains(T element)
    {
        SLNode<T> currNode = head;
        while (currNode != null)
        {
            if (currNode.getElement() == element)
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
        SLNode<T> currNode = head;
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

class MapEntry<K, V> {
    private K key;
    private V value;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class Map<K, V> {
    private SingleLinkedList<MapEntry<K, V>> entries;

    public Map() {
        entries = new SingleLinkedList<>();
    }

    public void put(K key, V value) {
        for (int i = 0; i < entries.size(); ++i) {
            MapEntry<K, V> entry = entries.get(i);
            if (entry.getKey() == key) {
                entry.setValue(value);
                return;
            }
        }
        entries.add(new MapEntry<>(key, value));
    }

    public V get(K key) {
        for (int i = 0; i < entries.size(); ++i) {
            MapEntry<K, V> entry = entries.get(i);
            if (entry.getKey() == key) {
                return entry.getValue();
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        for (int i = 0; i < entries.size(); ++i) {
            MapEntry<K, V> entry = entries.get(i);
            if (entry.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    public void remove(K key) {
        for (int i = 0; i < entries.size(); ++i) {
            MapEntry<K, V> entry = entries.get(i);
            if (entry.getKey() == key) {
                entries.remove(i);
                return;
            }
        }
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public int size() {
        return entries.size();
    }

    public void clear() {
        entries.clear();
    }

    public void printEntries()
    {
        entries.printList();
    }
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


public class PolynomialSolver implements IPolynomialSolver{
    private Map<Character, int[][]> polynomials;

    PolynomialSolver() {
        polynomials = new Map<>();
    }
    public void setPolynomial(char poly, int[][] terms)
    {
        if (terms == null)
        {
            System.out.println("Error");
            return;
        }
        polynomials.put(poly, terms);
    }
    public String print(char poly)
    {
        if (!polynomials.containsKey(poly))
        {
            return "Error";
        }
        int[][] polynomial = polynomials.get(poly);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < polynomial.length; ++i)
        {
            int coefficient = polynomial[i][0];
            int exponent = polynomial[i][1];

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
        if (!polynomials.containsKey(poly))
        {
            System.out.println("Error");
            return;
        }
        polynomials.remove(poly);
        printCoefficient(poly);
    }
    public float evaluatePolynomial(char poly, float value)
    {
        if (!polynomials.containsKey(poly))
        {
            System.out.println("Error");
            return Float.NaN;
        }

        int[][] polynomial = polynomials.get(poly);
        float result = 0;
        for (int i = 0; i < polynomial.length; ++i)
        {
            int coefficient = polynomial[i][0];
            int exponent = polynomial[i][1];
            result += coefficient * Math.pow(value, exponent);
        }

        return result;
    }
    public int[][] add(char poly1, char poly2)
    {
        if (!polynomials.containsKey(poly1) || !polynomials.containsKey(poly2))
        {
            System.out.println("Error");
            return null;
        }
        int[][] polynomial1 = polynomials.get(poly1);
        int[][] polynomial2 = polynomials.get(poly2);
        int[][] result = new int[Math.max(polynomial1.length, polynomial2.length)][2];

        int i = 0, j = 0, k = 0;
        while (k < result.length)
        {
            if(i < polynomial1.length && j < polynomial2.length)
            {
                if ((polynomial1[i][1] == polynomial2[j][1]))
                {
                    int coefficient = polynomial1[i][0] + polynomial2[j][0];
                    int exponent = polynomial1[i][1];

                    result[k][0] = coefficient;
                    result[k][1] = exponent;
                    ++k;
                    ++i;
                    ++j;
                }
                else if(polynomial1[i][1] > polynomial2[j][1])
                {
                    result[k][0] = polynomial1[i][0];
                    result[k][1] = polynomial1[i][1];;
                    ++k;
                    ++i;
                }
                else
                {
                    result[k][0] = polynomial2[j][0];
                    result[k][1] = polynomial2[j][1];;
                    ++k;
                    ++j;
                }
            }
            else if (j >= polynomial2.length)
            {
                result[k][0] = polynomial1[i][0];
                result[k][1] = polynomial1[i][1];;
                ++k;
                ++i;
            }
            else
            {
                result[k][0] = polynomial1[j][0];
                result[k][1] = polynomial1[j][1];;
                ++k;
                ++j;
            }
            
        }
        polynomials.put('R', result);
        return result;
    }
    public int[][] subtract(char poly1, char poly2)
    {
        if (!polynomials.containsKey(poly1) || !polynomials.containsKey(poly2))
        {
            System.out.println("Error");
            return null;
        }
        int[][] polynomial1 = polynomials.get(poly1);
        int[][] polynomial2 = polynomials.get(poly2);
        int[][] result = new int[Math.max(polynomial1.length, polynomial2.length)][2];

        int i = 0, j = 0, k = 0;
        while (k < result.length)
        {
            if(i < polynomial1.length && j < polynomial2.length)
            {
                if ((polynomial1[i][1] == polynomial2[j][1]))
                {
                    int coefficient = polynomial1[i][0] - polynomial2[j][0];
                    int exponent = polynomial1[i][1];

                    result[k][0] = coefficient;
                    result[k][1] = exponent;
                    ++k;
                    ++i;
                    ++j;
                }
                else if(polynomial1[i][1] > polynomial2[j][1])
                {
                    result[k][0] = polynomial1[i][0];
                    result[k][1] = polynomial1[i][1];;
                    ++k;
                    ++i;
                }
                else
                {
                    result[k][0] = -polynomial2[j][0];
                    result[k][1] = polynomial2[j][1];;
                    ++k;
                    ++j;
                }
            }
            else if (j >= polynomial2.length)
            {
                result[k][0] = polynomial1[i][0];
                result[k][1] = polynomial1[i][1];;
                ++k;
                ++i;
            }
            else
            {
                result[k][0] = -polynomial1[j][0];
                result[k][1] = polynomial1[j][1];;
                ++k;
                ++j;
            }
            
        }
        polynomials.put('R', result);
        return result;
    }
    public int[][] multiply(char poly1, char poly2)
    {
        if (!polynomials.containsKey(poly1) || !polynomials.containsKey(poly2))
        {
            System.out.println("Error");
            return null;
        }
        int[][] polynomial1 = polynomials.get(poly1);
        int[][] polynomial2 = polynomials.get(poly2);
        int[][] result = new int[polynomial1.length + polynomial2.length - 1][2];

        for (int i = 0; i < result.length; ++i)  
        { 
            result[i][0] = 0;
            result[i][1] = result.length - i - 1;
        }

        for (int i = 0; i < polynomial1.length; ++i)  
        { 
            for (int j = 0; j < polynomial2.length; ++j)  
            { 
                result[i + j][0] += polynomial1[i][0] * polynomial2[j][0]; 
            } 
        }
        
        polynomials.put('R', result);
        return result;
    }
    private void printCoefficient(char poly)
    {
        System.out.print("[");
        int[][] polynomial = polynomials.get(poly);

        if(polynomial != null)
        {
            for (int i = 0; i < polynomial.length; ++i)
            {
                int coefficient = polynomial[i][0];
                System.out.print(coefficient);
                if(i != polynomial.length - 1)
                System.out.print(", ");
            }
        }
        System.out.println("]");
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
                        if(r == null) break;
                        System.out.println(PS.print('R'));
                        break;
                    case "sub":
                        poly1 = sc.nextLine().charAt(0);
                        poly2 = sc.nextLine().charAt(0);
                        r = PS.subtract(poly1, poly2);
                        if(r == null) break;
                        System.out.println(PS.print('R'));
                        break;
                    case "mult":
                        poly1 = sc.nextLine().charAt(0);
                        poly2 = sc.nextLine().charAt(0);
                        r = PS.multiply(poly1, poly2);
                        if(r == null) break;
                        System.out.println(PS.print('R'));
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