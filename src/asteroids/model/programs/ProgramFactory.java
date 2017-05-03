package asteroids.model.programs;

import java.util.List;
import java.util.Optional;

import asteroids.model.Asteroid;
import asteroids.model.Bullet;
import asteroids.model.Entity;
import asteroids.model.MinorPlanet;
import asteroids.model.Planetoid;
import asteroids.model.Program;
import asteroids.model.Ship;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		// TODO Auto-generated method stub
		return new Program(functions, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName,
			Statement body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new Function(functionName, body, sourceLocation);
	}

	@Override
	public Statement createAssignmentStatement(String variableName,
			Expression value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new AssignmentStatement(variableName, value, sourceLocation);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new WhileStatement(condition, body, sourceLocation);
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new BreakStatement(sourceLocation);
	}

	@Override
	public Statement createReturnStatement(Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReturnStatement(value, sourceLocation);
	}

	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody,
			Statement elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new IfStatement(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrintStatement(Expression value,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new PrintStatement(value, sourceLocation);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new SequenceStatement(statements, sourceLocation);
	}

	@Override
	public Expression createReadVariableExpression(String variableName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReadVariableExpression(variableName, sourceLocation);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ReadParameterExpression(parameterName, sourceLocation);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName,
			List<Expression> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new FunctionCallExpression(functionName, actualArgs, sourceLocation);
	}

	@Override
	public Expression<Double> createChangeSignExpression(Expression expression,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new ChangeSignExpression(expression, sourceLocation);
	}

	@Override
	public Expression<Boolean> createNotExpression(Expression expression,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return new NotExpression(expression, sourceLocation);
	}

	@Override
	public Expression<Double> createDoubleLiteralExpression(double value,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new DoubleLiteralExpression(value, location);
	}

	@Override
	public Expression<Entity> createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Entity evaluate() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String toString() {
				return "[NullExpression]";
			}
		};
	}

	@Override
	public Expression<Entity> createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Ship evaluate() {
				// TODO Auto-generated method stub
				return getProgram().getShip();
			}
			
			@Override
			public String toString() {
				return "[SelfExpression]";
			}
		};
	}

	@Override
	public Expression<Entity> createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Ship evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Ship> closestShip = self.getWorld().getShips().stream().filter(ship -> !ship.equals(self)).
					reduce((ship1, ship2) -> (self.getDistanceBetween(ship1) < self.getDistanceBetween(ship2) ? ship1 : ship2));
				if (closestShip.isPresent()) return closestShip.get();
				return null;
			}
			
			@Override
			public String toString() {
				return "[ShipExpression]";
			}
			
		};
	}

	@Override
	public Expression<Entity> createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Asteroid evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Asteroid> closestAsteroid = self.getWorld().getAsteroids().stream().
					reduce((asteroid1, asteroid2) -> (self.getDistanceBetween(asteroid1) < self.getDistanceBetween(asteroid2) ? asteroid1 : asteroid2));
				if (closestAsteroid.isPresent()) return closestAsteroid.get();
				return null;
			}
			
			@Override
			public String toString() {
				return "[AsteroidExpression]";
			}
			
		};
	}

	@Override
	public Expression<Entity> createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Planetoid evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Planetoid> closestPlanetoid = self.getWorld().getPlanetoids().stream().
						reduce((planetoid1, planetoid2) -> (self.getDistanceBetween(planetoid1) < self.getDistanceBetween(planetoid2) ? planetoid1 : planetoid2));
					if (closestPlanetoid.isPresent()) return closestPlanetoid.get();
					return null;
			}
			
			@Override
			public String toString() {
				return "[PlanetoidExpression]";
			}
			
		};
	}

	@Override
	public Expression<Entity> createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Bullet evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Bullet> closestBullet = self.getWorld().getBullets().stream().filter(bullet -> bullet.getSource() == self).
						reduce((bullet1, bullet2) -> (self.getDistanceBetween(bullet1) < self.getDistanceBetween(bullet2) ? bullet1 : bullet2));
					if (closestBullet.isPresent()) return closestBullet.get();
					return null;
			}
			
			@Override
			public String toString() {
				return "[BulletExpression]";
			}
			
		};
	}

	@Override
	public Expression<Entity> createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public MinorPlanet evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Entity> closestPlanet = self.getWorld().getEntities().stream().filter(entity -> entity instanceof MinorPlanet).
						reduce((planet1, planet2) -> (self.getDistanceBetween(planet1) < self.getDistanceBetween(planet2) ? planet1 : planet2));
					if (closestPlanet.isPresent()) return (MinorPlanet)closestPlanet.get();
					return null;
			}
			
			@Override
			public String toString() {
				return "[PlanetExpression]";
			}
			
		};
	}

	@Override
	public Expression<Entity> createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new EntityExpression(location){

			@Override
			public Entity evaluate() {
				// TODO Auto-generated method stub
				Ship self = getProgram().getShip();
				Optional<Entity> anyEntity = self.getWorld().getEntities().stream().findAny();
					if (anyEntity.isPresent()) return anyEntity.get();
					return null;
			}
			
			@Override
			public String toString() {
				return "[AnyExpression]";
			}
			
		};
	}

	@Override
	public Expression<Double> createGetXExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetXExpression(e, location);
	}

	@Override
	public Expression<Double> createGetYExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetYExpression(e, location);
	}

	@Override
	public Expression<Double> createGetVXExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetVXExpression(e, location);
	}

	@Override
	public Expression<Double> createGetVYExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetVYExpression(e, location);
	}

	@Override
	public Expression<Double> createGetRadiusExpression(Expression e,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetRadiusExpression(e, location);
	}

	@Override
	public Expression<Boolean> createLessThanExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new LessThanExpression(e1, e2, location);
	}

	@Override
	public Expression<Boolean> createEqualityExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new EqualityExpression(e1, e2, location);
	}

	@Override
	public Expression<Double> createAdditionExpression(Expression e1, Expression e2,
			SourceLocation location) {
		// TODO Auto-generated method stub
		//return new AdditionExpression(e1, e2, location);
		return new AdditionExpression(e1, e2, location);
	}

	@Override
	public Expression<Double> createMultiplicationExpression(Expression e1,
			Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new MultiplicationExpression(e1, e2, location);
	}

	@Override
	public Expression<Double> createSqrtExpression(Expression e, SourceLocation location) {
		// TODO Auto-generated method stub
		
		return new SqrtExpression(e, location);
	}

	@Override
	public Expression<Double> createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetDirectionExpression(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ActionStatement(location) {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				setFailedToAdvanceTime(false);
				getProgram().setCurrentLocation(getSourceLocation());
				if (getProgram().getTimer() < 0.2) {
					setFailedToAdvanceTime(true);
					return;
				}
				getProgram().getShip().thrustOn();
				getProgram().advanceTimer();
			}
			
			@Override
			public String toString() {
				return "[ThrustOnStatement]";
			}
		};
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ActionStatement(location) {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				setFailedToAdvanceTime(false);
				getProgram().setCurrentLocation(getSourceLocation());
				if (getProgram().getTimer() < 0.2) {
					setFailedToAdvanceTime(true);
					return;
				}
				getProgram().getShip().thrustOff();
				getProgram().advanceTimer();
			}
			
			@Override
			public String toString() {
				return "[ThrustOffStatement]";
			}
		};
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ActionStatement(location) {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				setFailedToAdvanceTime(false);
				getProgram().setCurrentLocation(getSourceLocation());
				if (getProgram().getTimer() < 0.2) {
					setFailedToAdvanceTime(true);
					return;
				}
				getProgram().getShip().fireBullet();
				getProgram().advanceTimer();
			}
			
			@Override
			public String toString() {
				return "[FireStatement]";
			}
		};
	}

	@Override
	public Statement createTurnStatement(Expression angle,
			SourceLocation location) {
		// TODO Auto-generated method stub
		return new ActionStatement(location) {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				setFailedToAdvanceTime(false);
				getProgram().setCurrentLocation(getSourceLocation());
				if (getProgram().getTimer() < 0.2) {
					setFailedToAdvanceTime(true);
					return;
				}
				if(!Ship.isValidOrientation(getProgram().getShip().getOrientation()+(double)angle.evaluate())) throw new IllegalArgumentException("Invalid angle");
				getProgram().getShip().turn((Double)angle.evaluate());
				getProgram().advanceTimer();
			}
			
			@Override
			public String toString() {
				return "[TurnStatement: " + angle.toString() + "]";
			}
		};
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return new ActionStatement(location) {

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				setFailedToAdvanceTime(false);
				getProgram().setCurrentLocation(getSourceLocation());
				if (getProgram().getTimer() < 0.2) {
					setFailedToAdvanceTime(true);
					return;
				}
				getProgram().advanceTimer();
			}
			
			@Override
			public String toString() {
				return "[SkipStatement]";
			}
		};
	}


}
