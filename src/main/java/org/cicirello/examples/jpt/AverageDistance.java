/*
 * Example programs for JavaPermutationTools library.
 * Copyright (C) 2018-2023 Vincent A. Cicirello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.cicirello.examples.jpt;

import org.cicirello.permutations.Permutation;
import org.cicirello.permutations.distance.*;

/**
 * Simple example illustrating: (a) constructing random permutations of a given length, and (b)
 * computing the distance between a pair of permutations. For several of the permutation distances
 * in the library, the example computes the average distance between pairs of random permutations.
 *
 * <p>For the user of this example who is unfamiliar with the statistics of permutations, you should
 * expect the resulting averages to be high. Random permutations tend to be very dissimilar. For
 * example, the average number of fixed points in random permutations (a fixed point is a position
 * that contains the same element) is 1 regardless of the length of the permutations. So since
 * ExactMatchDistance is the count of the number of positions that contain different elements, on
 * average we should expect (LENGTH - 1) positions in a pair of random permutations to contain
 * different elements. You should expect a similar effect for the other distance metrics, though
 * they measure different characteristics of the permutations.
 *
 * <p>There are two optional command line arguments. If you specify no command line arguments, then
 * this defaults to permutations of length 100, and uses 100 pairs of random permutations to compute
 * the averages. The first command line argument is used to specify permutation length, and the
 * second is used to specify number of samples to use for computing averages.
 *
 * @author <a href=https://www.cicirello.org/ target=_top>Vincent A. Cicirello</a>, <a
 *     href=https://www.cicirello.org/ target=_top>https://www.cicirello.org/</a>
 */
public class AverageDistance {

  /**
   * Runs the example program.
   *
   * @param args The command line arguments are optional. If specified, the first command line
   *     argument is the permutation length (defaults to 100 if not specified), and the second
   *     command line argument is the number of samples to use to compute averages (defaults to 100
   *     if not specified).
   */
  public static void main(String[] args) {
    ExamplesShared.printCopyrightAndLicense();

    // permutation length
    final int LENGTH = (args.length > 0) ? Integer.parseInt(args[0]) : 100;

    // number of samples to use to compute averages
    final int NUM_SAMPLES = (args.length > 1) ? Integer.parseInt(args[1]) : 100;

    // list of distance metrics used in example
    PermutationDistanceMeasurer[] d = {
      new AcyclicEdgeDistance(),
      new CyclicEdgeDistance(),
      new RTypeDistance(),
      new CyclicRTypeDistance(),
      new DeviationDistance(),
      new SquaredDeviationDistance(),
      new LeeDistance(),
      new KendallTauDistance(),
      new ReinsertionDistance(),
      new ExactMatchDistance(),
      new InterchangeDistance()
    };

    System.out.println("Example computes average distance between pairs of random permutations.");
    System.out.printf("Permutation length: %d%n", LENGTH);
    System.out.printf("Number of samples used in averages: %d%n%n", NUM_SAMPLES);

    // for each distance metric
    for (int i = 0; i < d.length; i++) {
      int sum = 0;
      for (int j = 0; j < NUM_SAMPLES; j++) {
        // generate a pair of random permutations of specified length
        Permutation p1 = new Permutation(LENGTH);
        Permutation p2 = new Permutation(LENGTH);
        // add their distance to the sum
        sum += d[i].distance(p1, p2);
      }
      // compute average
      double average = 1.0 * sum / NUM_SAMPLES;
      // output result
      System.out.printf("%s: %.2f%n", d[i].getClass().getSimpleName(), average);
    }
  }
}
