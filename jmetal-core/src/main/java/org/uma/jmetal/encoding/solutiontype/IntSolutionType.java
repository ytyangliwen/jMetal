//  IntSolutionType.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//
//  Copyright (c) 2014 Antonio J. Nebro
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
// 
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.uma.jmetal.encoding.solutiontype;

import org.uma.jmetal.core.Problem;
import org.uma.jmetal.core.Solution;
import org.uma.jmetal.core.SolutionType;
import org.uma.jmetal.core.Variable;
import org.uma.jmetal.encoding.variable.Int;

/**
 * Class representing the solutiontype type of solutions composed of Int variables
 */
public class IntSolutionType extends SolutionType implements IntSolutionTypeTemplate {

  /**
   * Constructor
   *
   * @param problem Problem to solve
   */
  public IntSolutionType(Problem problem) {
    super(problem);
  }

  /**
   * Creates the variables of the solutiontype
   */
  public Variable[] createVariables() {
    Variable[] variables = new Variable[getProblem().getNumberOfVariables()];

    for (int var = 0; var < getProblem().getNumberOfVariables(); var++) {
      variables[var] = new Int((int) getProblem().getLowerLimit(var),
        (int) getProblem().getUpperLimit(var));
    }

    return variables;
  }
  
  @Override
  public int getIntValue(Solution solution, int index) {
    return (int)solution.getDecisionVariables()[index].getValue() ;
  }

  @Override public void setIntValue(Solution solution, int index, int value) {
    solution.getDecisionVariables()[index].setValue(value) ;
  }

  @Override public int getNumberOfIntVariables(Solution solution) {
    return solution.getDecisionVariables().length ;
  }

  @Override public int getIntUpperBound(Solution solution, int index) {
    return (int)solution.getDecisionVariables()[index].getUpperBound() ;
  }

  @Override public int getIntLowerBound(Solution solution, int index) {
    return (int)solution.getDecisionVariables()[index].getLowerBound() ;
  }
}