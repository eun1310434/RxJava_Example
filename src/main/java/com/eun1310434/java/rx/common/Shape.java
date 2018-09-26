package com.eun1310434.java.rx.common;

public class Shape {
	
	public static final String HEXAGON = "HEXAGON";
	public static final String OCTAGON = "OCTAGON";
	public static final String RECTANGLE = "RECTANGLE";
	public static final String TRIANGLE = "TRIANGLE";
	public static final String DIAMOND = "DIAMOND";
	public static final String PENTAGON = "PENTAGON";
	public static final String BALL = "BALL";	
	public static final String STAR = "STAR";
	public static final String NO_SHAPE = "NO_SHAPE";
	public static final String FLIPPED = "(flipped)";
	
	//Colors for shape 
	public static final String RED = "RED";
	public static final String YELLOW = "YELLOW";
	public static final String GREEN = "GREEN";
	public static final String SKY = "SKY";
	public static final String BLUE = "BLUE";
	public static final String PUPPLE = "PUPPLE";	
	public static final String ORANGE = "ORANGE";	
	
	public static String getColor(String shape) { 
		if (shape.endsWith("<>")) //diamond 
			return shape.replace("<>", "").trim();
		
		int hyphen = shape.indexOf("-");
		if (hyphen > 0) {
			return shape.substring(0, hyphen);
		}
		
		return shape; //for ball 
	}

	public static String getSuffix(String shape) { 
		if (HEXAGON.equals(shape)) return "-H";
		if (OCTAGON.equals(shape)) return "-O"; 
		if (RECTANGLE.equals(shape)) return "-R";
		if (TRIANGLE.equals(shape)) return "-T";
		if (DIAMOND.equals(shape)) return "-D";
		if (PENTAGON.equals(shape)) return "-P";
		if (STAR.equals(shape)) return "-S";
		if (BALL.equals(shape)) return "-B";
		return ""; //for BALL		
	}

	public static String getShape(String obj) {
		if (obj == null || obj.equals("")) return NO_SHAPE;		
		if (obj.endsWith("-H")) return HEXAGON;
		if (obj.endsWith("-O")) return OCTAGON;
		if (obj.endsWith("-R")) return RECTANGLE;
		if (obj.endsWith("-T")) return TRIANGLE;
		if (obj.endsWith("-D")) return DIAMOND;
		if (obj.endsWith("-P")) return PENTAGON;
		if (obj.endsWith("-S")) return STAR;
		if (obj.endsWith("-B")) return BALL;
		return "BALL";
	}	
	
	public static String getString(String color, String shape) { 
		return color + getSuffix(shape);
	}
	
	public static String flip(String item) throws ShapeCannotFlipException { 
		if(item.startsWith(FLIPPED)) { 
			return item.replace(FLIPPED, "");
		}
		
		String shape = getShape(item);
		switch(shape) { 
		case BALL:
		case RECTANGLE:
		case DIAMOND: 
		case NO_SHAPE:
			throw new ShapeCannotFlipException();
			//return "throw new ShapeCannotFlipException()";
		};
		
		return FLIPPED + item;		
	}	
	
	public static String triangle(String color) { 
		return color + "-T";
	}

	public static String rectangle(String color) { 
		return color + "-R";
	}	
	
	public static String star(String color) { 
		return color + "-S";
	}
	
	public static String pentagon(String color) { 
		return color + "-P";
	}
}