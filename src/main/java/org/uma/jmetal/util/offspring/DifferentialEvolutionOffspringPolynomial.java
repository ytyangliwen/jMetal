/*
 * DifferentialEvolutionOffspringPolynomial.java
 *
 * @author Antonio J. Nebro
 * @version 1.0
 *
 * This class returns a solution after applying SBX and Polynomial mutation
 */
package org.uma.jmetal.util.offspring;

import org.uma.jmetal.core.Operator;
import org.uma.jmetal.core.Solution;
import org.uma.jmetal.core.SolutionSet;
import org.uma.jmetal.operator.mutation.MutationFactory;
import org.uma.jmetal.operator.selection.SelectionFactory;
import org.uma.jmetal.util.Configuration;
import org.uma.jmetal.util.JMetalException;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DifferentialEvolutionOffspringPolynomial extends Offspring {

  double crossoverProbability_ = 0.9;
  double distributionIndexForCrossover_ = 20;
  private double mutationProbability_ = 0.0;
  private double distributionIndexForMutation_ = 20;
  private Operator mutation_;
  private Operator selection_;

  private DifferentialEvolutionOffspringPolynomial(double mutationProbability,
    double distributionIndexForMutation
  ) throws JMetalException {
    mutationProbability_ = mutationProbability;
    distributionIndexForMutation_ = distributionIndexForMutation;

    HashMap<String, Object> mutationParameters = new HashMap<String, Object>();
    mutationParameters.put("probability", mutationProbability_);
    mutationParameters.put("distributionIndex", distributionIndexForMutation_);
    mutation_ = MutationFactory.getMutationOperator("PolynomialMutation", mutationParameters);

    selection_ = SelectionFactory.getSelectionOperator("BinaryTournament", null);

    id_ = "Polynomial";
  }

  public Solution getOffspring(SolutionSet solutionSet) {
    Solution[] parents = new Solution[2];
    Solution offSpring = null;

    try {
      offSpring = new Solution((Solution) selection_.execute(solutionSet));


      mutation_.execute(offSpring);
      //Create a new solutiontype, using DE
    } catch (JMetalException ex) {
      Logger.getLogger(DifferentialEvolutionOffspringPolynomial.class.getName())
        .log(Level.SEVERE, null, ex);
    }
    return offSpring;

  } // getOffspring

  public Solution getOffspring(SolutionSet solutionSet, SolutionSet archive) {
    Solution[] parents = null;
    Solution offSpring = null;

    try {
      // FIXME: this will return an exception
      parents[0] = (Solution) selection_.execute(solutionSet);

      if (archive.size() > 0) {
        parents[1] = (Solution) selection_.execute(archive);
      } else {
        parents[1] = (Solution) selection_.execute(solutionSet);
      }

      offSpring = new Solution(new Solution((Solution) selection_.execute(solutionSet)));


      mutation_.execute(offSpring);
      //Create a new solutiontype, using DE
    } catch (JMetalException ex) {
      Logger.getLogger(DifferentialEvolutionOffspringPolynomial.class.getName())
        .log(Level.SEVERE, null, ex);
    }
    return offSpring;

  } // getOffpring

  public Solution getOffspring(Solution solution) {
    Solution res = new Solution(solution);
    try {
      mutation_.execute(res);
    } catch (JMetalException e) {
      Configuration.logger_.log(Level.SEVERE, "Error", e);
    }
    return res;

  }
} 
