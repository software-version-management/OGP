package asteroids.model.programs;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement {

	public BreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated constructor stub
		super(sourceLocation);
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String toString() {
		return "[BreakStatement]";
	}

	@Override
	public void setProgram(Program program) {
		// TODO Auto-generated method stub
		super.setProgram(program);
	}
	
	public boolean hasActiveBreakStatement(){
		return true;
	}

}
