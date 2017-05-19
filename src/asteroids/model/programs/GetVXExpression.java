package asteroids.model.programs;

import java.util.List;
import java.util.Set;

import asteroids.model.Entity;
import asteroids.model.Program;
import asteroids.part3.programs.SourceLocation;

public class GetVXExpression extends Expression<Double> {
	private Expression<? extends Entity> e;
	
	public GetVXExpression(Expression<? extends Entity> e, SourceLocation location) {
		// TODO Auto-generated constructor stub
		super(location);
		this.e = e;
	}

	@Override
	public Double evaluate() throws IllegalArgumentException {
		return e.evaluate().getVelocity()[0];
	}

	@Override
	public Double evaluate(List<Expression> actualArgs, Set<Variable> localVariables) throws IllegalArgumentException {
		return e.evaluate(actualArgs, localVariables).getVelocity()[0];
	}

	@Override
	public void setProgram(Program program) {
		super.setProgram(program);
		e.setProgram(program);
	}
	
	@Override
	public String toString() {
		return "[GetVXExpression: " + e.toString() + "]";
	}

}
