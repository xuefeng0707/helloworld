package antlr;

import org.antlr.Tool;

public class Antlr2Java {

	public static void main(String[] args) {
		System.out.println();
		Tool.main(new String[]{"-o", "src/main/java/antlr", "src/main/java/antlr/Expr.g"});
	}

}
