package asteroids.model.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	private SourceLocation sourceLocation;
	private Program program;
	
	protected Expression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	public abstract T evaluate() throws IllegalArgumentException;
	
	public abstract T evaluate(List<Expression> actualArgs, Set<Variable> localVariables) throws IllegalArgumentException;

	public void setProgram(Program program){
		this.program = program;
	}
	
	public Program getProgram(){
		return program;
	}

}
