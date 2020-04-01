package eg.edu.alexu.csd.datastructure.stack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEvaluator implements IExpressionEvaluator {
	private int precidence(String c) {
			    
			    if(c.equals("+")||c.equals("-"))
			        return 1;

			    else if(c.equals("*")||c.equals("/"))
			        return 2;

			    else if (c.equals("^"))
			        return 3;
			    
			    return -1;
	}
	private static boolean isOperator(String c) {
		if(c.equals("+")||c.equals("-")||c.equals("*")||c.equals("/")||c.equals("^")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private  boolean isFloat(String c) {
		String regex="[+-]?[0-9]+(\\.[0-9]+)?([Ee][+-]?[0-9]+)?";
        Pattern p = Pattern.compile(regex);  
        Matcher m = p.matcher(c); 
           
        if(m.find() && m.group().equals(c)) {
            return true; 
        }
        else {
            return false; 
        }
	}
	private static boolean isOperatorChar(char c) {
		if(c=='+'||c=='-'||c=='*'||c=='/'||c=='^'||c=='('||c==')') {
			return true;
		}
		else {
			return false;
		}
	}
	private String[] Convresion(String Ex) {
		char[] splitted=Ex.toCharArray();
		String[] finale =new String[splitted.length+1];
		for(int j=0;j<finale.length;j++) {
			finale[j]="";
		}
		int k=0;
		for(int i=0;i<splitted.length;i++) {
			if(!isOperatorChar(splitted[i])||(splitted[i]=='-'&&i==0)) {
				String temp=Character.toString(splitted[i]);
				finale[k]=finale[k].concat(temp);
			}
			else if(splitted[i]=='(') {
				String temp=Character.toString(splitted[i]);
				finale[k]=finale[k].concat(temp);
				k++;
			}
			else if(splitted[i]=='-'&&(i!=splitted.length-1)&&i!=0) {
				if(!finale[k].equals("")) {
					k++;
				}
				if(isOperatorChar(splitted[i-1])) {
					String temp=Character.toString(splitted[i]);
					finale[k]=finale[k].concat(temp);
				}
				else {
					String temp=Character.toString(splitted[i]);
					finale[k]=finale[k].concat(temp);
					k++;
				}
			}
			
			else {
				if(!finale[k].equals("")) {
					k++;
				}
				String temp=Character.toString(splitted[i]);
				finale[k]=finale[k].concat(temp);
				k++;
			}
		}
	return finale;
	}
	
	public  String infixToPostfix(String expression) {
		// TODO Auto-generated method stub
		Stack s=new Stack();
		String postfix="";
		String operatr;
		expression=expression.replaceAll(" ", "");
		
		String Splitted[]=Convresion(expression);
		for(int i=0;!Splitted[i].equals("");i++) {
			if (Splitted[i].equals("("))
	        {

	            s.push(Splitted[i]);
	        }

	        else if (Splitted[i].equals(")"))
	        {
	            while ( !s.peek().equals("("))
	            {
	                operatr=(String) s.pop();
	                postfix= postfix.concat(operatr);
	                postfix=postfix.concat(" ");
	            }
	            s.pop();//remove opening bracket

	        }

	        else if (isOperator(Splitted[i]))
	        {

	            if (s.isEmpty() ||( s.peek()==")"|| s.peek()=="("))  //if empty
	            {
	                    s.push(Splitted[i]);   
	            }

	            else if (precidence(Splitted[i])<=precidence((String) s.peek()))
	            {
	                    operatr=(String) s.pop();
	                    postfix=postfix.concat(operatr);
	                    postfix=postfix.concat(" ");
	                    s.push(Splitted[i]);

	            }
	            else if ((precidence(Splitted[i])>precidence((String) s.peek()))&& ( !Splitted[i-1].equals("(") || !Splitted[i-1].equals(")")))
	            {
	            	
	                s.push(Splitted[i]);

	            }

	        }
	        else if (!isFloat(Splitted[i]))
	        {
	            System.out.println("entered expression cant have alphabets");
	        }

	        else if (isFloat(Splitted[i]))  //if not letter then number
	        {
	        	postfix=postfix.concat(Splitted[i]);
	        	postfix=postfix.concat(" ");
	        }

	    }
	    while (!s.isEmpty()) //pop all when reach end
	    {
	        operatr=(String) s.pop();
	        postfix=postfix.concat(operatr);
	        postfix=postfix.concat(" ");


	    }
	    return postfix;
	}
		
	

	@Override
	public int evaluate(String expression) {
		// TODO Auto-generated method stub
		Stack s =new Stack();
		String Splitted[]=expression.split("\\s+");
		float []operation=new float[2];
		float result;
		String temp,temp2;
		String holder;
		for(int i=0;i<Splitted.length;i++) {
		    if (isOperator(Splitted[i])) //if normal operator
	        {
		    	try {
		    	     temp=(String) s.pop();
		    	}catch(RuntimeException e) {
		    		 temp="0";
		    	}
		    	try {
		    		 temp2=(String) s.pop();
		    	}catch(RuntimeException e) {
		    		 temp2="0";
		    	}
		    	
	            operation[0]= Float.parseFloat(temp);
	            operation[1]=Float.parseFloat(temp2);

	            switch (Splitted[i])
	            {
	            case "+":
	                result=operation[1]+operation[0];
	                holder=String.valueOf(result);
	                s.push(holder);
	                break;

	            case "-":
	                result=operation[1]-operation[0];
	                holder=String.valueOf(result);
	                s.push(holder);
	                break;

	            case "*":
	                result=operation[1]*operation[0];
	                holder=String.valueOf(result);
	                s.push(holder);
	                break;


	            case "/":
	                result=operation[1]/operation[0];
	                holder=String.valueOf(result);
	                s.push(holder);
	                break;


	            case "^":
	                result=(float) Math.pow(operation[1],operation[0]);
	                holder=String.valueOf(result);
	                s.push(holder);
	                break;
	            }


	        }
	        else
	        {
	            s.push(Splitted[i]);
	        }

		}
		float temp1= Float.parseFloat((String) s.pop());
		if(temp1>0) {
			temp1=(float) (temp1+.5);
		}
		else {
			temp1=(float) (temp1-.5);
		}
		return (int) temp1;
	}

}

