import java.util.ArrayList;

public class OperationHandler {
	
	private String operation = "";
	
	private String operands = "AB";
	private String operators = "'.*+-x";
	private String numbers = "0123456789";
	
	private MatrixOps ops = new MatrixOps();
	
	public void operate(String op) {
		if (op.equals("delete")) {
			if (operation.length() > 0)
				operation = operation.substring(0, operation.length() - 1);
		}
		else if (op.equals("clear"))
			operation = "";
		else if (op.equals("'")) {
			if (operation.length() > 0 && operands.contains(operation.charAt(operation.length() - 1) + ""))
				operation += op;
		}
		else if (op.equals("+") || op.equals("-") || op.equals("x")) {
			if (operation.length() > 0 && (!operators.contains(operation.charAt(operation.length() - 1) + "") || operation.charAt(operation.length() - 1) == '\''))
				operation += op;
		}
		else if (op.equals(".")) {
			if ( operation.length() > 0 && numbers.contains(operation.charAt(operation.length() - 1) + ""))
				operation += op;
		}
		else if (operands.contains(op)) {
			if (operation.length() == 0 || operators.contains(operation.charAt(operation.length() - 1) + ""))
				operation += op;
		}
		else if (numbers.contains(op))
			operation += op;
	}
	
	public String getOperation() {
		return operation;
	}
	
	public void evaluate() {
		String temp = operation;
		double[][] tempA = MatrixA.getA();
		double[][] tempB = MatrixB.getB();
		//32.123xB
		while (temp.contains("'")) {
			if (temp.charAt(temp.indexOf("'") - 1) == 'A') {
				tempA = ops.invert(tempA);
				temp = temp.substring(0, temp.indexOf("'")) + temp.substring(temp.indexOf("'") + 1);
			}
			else if (temp.charAt(temp.indexOf("'") - 1) == 'B') {
				tempB = ops.invert(tempB);
				temp = temp.substring(0, temp.indexOf("'")) + temp.substring(temp.indexOf("'") + 1);
			}
		}
		
		String operators = "+-x#";
		ArrayList<String> oprs = new ArrayList<>();
		ArrayList<String> opts = new ArrayList<>();
		temp += "#";
		for (int i = 0; i < temp.length(); i++) {
			if (operators.contains(temp.charAt(i) + "")) {
				oprs.add(temp.substring(0, i));
				opts.add(temp.charAt(i) + "");
				temp = temp.substring(i + 1);
				i = 0;
			}
		}
		opts.remove("#");

		if (oprs.size() == 1) {
			if (oprs.get(0).equals("A")) {
				System.out.println("a");
				new ResultWindow(tempA);
			}
			else if (oprs.get(0).equals("B"))
				new ResultWindow(tempB);
			return;
		}
		
		double[][] C = null;
		while (opts.contains("x")) {
			String op1, op2;
			if (opts.contains("x")) {
				int index = opts.indexOf("x");
				opts.remove(index);
				op1 = oprs.remove(index);
				op2 = oprs.remove(index);
				if (op1.equals("A") && !(op2.equals("A") || op2.equals("B") || op2.equals("C"))) {
					tempA = ops.multiply(tempA, Double.parseDouble(op2));
					if (tempA == null)
						return;
					oprs.add(index, op1);
				}
				else if (op1.equals("B") && !(op2.equals("A") || op2.equals("B") || op2.equals("C"))) {
					tempB = ops.multiply(tempB, Double.parseDouble(op2));
					if (tempB == null)
						return;
					oprs.add(index, op1);
				}
				else if (op1.equals("C") && !(op2.equals("A") || op2.equals("B") || op2.equals("C"))) {
					C = ops.multiply(C, Double.parseDouble(op2));
					if (C == null)
						return;
					oprs.add(index, op1);
				}
				else if (op2.equals("A") && !(op1.equals("A") || op1.equals("B") || op1.equals("C"))) {
					tempA = ops.multiply(tempA, Double.parseDouble(op1));
					if (tempA == null)
						return;
					oprs.add(index, op2);
				}
				else if (op2.equals("B") && !(op1.equals("A") || op1.equals("B") || op1.equals("C"))) {
					tempB = ops.multiply(tempB, Double.parseDouble(op1));
					if (tempB == null)
						return;
					oprs.add(index, op2);
				}
				else if (op2.equals("C") && !(op1.equals("A") || op1.equals("B") || op1.equals("C"))) {
					C = ops.multiply(C, Double.parseDouble(op1));
					if (C == null)
						return;
					oprs.add(index, op2);
				}
				else if (op1.equals("A") && op2.equals("B")) {
					C = ops.multiply(tempA, tempB);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
				else if (op1.equals("B") && op2.equals("A")) {
					C = ops.multiply(tempA, tempB);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
				else if (op1.equals("A") && op2.equals("C")) {
					C = ops.multiply(tempA, C);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
				else if (op1.equals("C") && op2.equals("A")) {
					C = ops.multiply(C, tempA);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
				else if (op1.equals("C") && op2.equals("B")) {
					C = ops.multiply(C, tempB);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
				else if (op1.equals("B") && op2.equals("C")) {
					C = ops.multiply(tempB, C);
					if (C == null)
						return;
					oprs.add(index, "C");
				}
			}
		}
		if (oprs.size() == 1) {
			if (oprs.get(0).equals("C"))
				new ResultWindow(C);
			else if (oprs.get(0).equals("A"))
				new ResultWindow(tempA);
			else if (oprs.get(0).equals("B"))
				new ResultWindow(tempB);
			return;
		}
		
		while (opts.size() > 0) {
			String op1 = oprs.remove(0);
			String op2 = oprs.remove(0);
			String opt = opts.remove(0);
			if (opt.equals("+")) {
				if ((op1.equals("A") && op2.equals("B")) || (op1.equals("B") && op2.equals("A")))
					C = ops.sum(tempA, tempB);
				else if ((op1.equals("A") && op2.equals("C")) || (op1.equals("C") && op2.equals("A")))
					C = ops.sum(tempA, C);
				else if ((op1.equals("B") && op2.equals("C")) || (op1.equals("C") && op2.equals("B")))
					C = ops.sum(tempB, C);
				oprs.add(0, "C");
			}
			else if (opt.equals("-")) {
				if ((op1.equals("A") && op2.equals("B")) || (op1.equals("B") && op2.equals("A")))
					C = ops.substract(tempA, tempB);
				else if ((op1.equals("A") && op2.equals("C")) || (op1.equals("C") && op2.equals("A")))
					C = ops.substract(tempA, C);
				else if ((op1.equals("B") && op2.equals("C")) || (op1.equals("C") && op2.equals("B")))
					C = ops.substract(tempB, C);
				oprs.add(0, "C");
			}
		}
		new ResultWindow(C);
	}
}
