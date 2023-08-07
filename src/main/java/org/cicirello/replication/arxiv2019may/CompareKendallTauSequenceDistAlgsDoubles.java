/*
 * Example programs for JavaPermutationTools library.
 * Copyright (C) 2019-2023 Vincent A. Cicirello
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
package org.cicirello.replication.arxiv2019may;

import java.lang.management.*;
import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadLocalRandom;
import org.cicirello.examples.jpt.ExamplesShared;
import org.cicirello.sequences.distance.KendallTauSequenceDistance;

/**
 * This program replicates the data for the paper:<br>
 * V.A. Cicirello, <a href="https://www.cicirello.org/publications/cicirello2019arXiv.html"
 * target=_top>"Kendall Tau Sequence Distance: Extending Kendall Tau from Ranks to Sequences,"</a>
 * arXiv preprint arXiv:1905.02752 [cs.DM], May 2019.
 *
 * @author <a href=https://www.cicirello.org/>Vincent A. Cicirello</a>, <a
 *     href=https://www.cicirello.org/>https://www.cicirello.org/</a>
 */
public class CompareKendallTauSequenceDistAlgsDoubles {

  private static int toTime(KendallTauSequenceDistance d, double[][] a, double[][] b) {
    // Summing and returning the sum of distances is
    // just to prevent the Java JIT from considering this dead code and optimizing it away.
    int total = 0;
    for (int i = 0; i < a.length; i++) {
      int distance = d.distance(a[i], b[i]);
      total += distance;
    }
    return total;
  }

  /**
   * Runs the replication program.
   *
   * @param args Ignored, because there are no command line arguments.
   */
  public static void main(String[] args) {
    ExamplesShared.printCopyrightAndLicense();

    KendallTauSequenceDistance dHash = new KendallTauSequenceDistance();
    KendallTauSequenceDistance dSort = new KendallTauSequenceDistance(true);
    final int MIN_L = 256;
    final int MAX_L = 131072;
    final int N = 100;
    int[] alphabetSize = {1, 4, 16, 64, 256, 1024, 4096, 16384, 65536};
    ThreadMXBean bean = ManagementFactory.getThreadMXBean();
    bean.setThreadCpuTimeEnabled(true);
    // WARM UP THE JAVA JIT.
    double[][] iArrays = new double[N * N][];
    double[][] iShuffled = new double[N * N][];
    for (int i = 0; i < iArrays.length; i++) {
      iArrays[i] = randDoubleSequence(1000, 100);
      iShuffled[i] = shuffleCopy(iArrays[i]);
    }
    toTime(dHash, iArrays, iShuffled);
    toTime(dSort, iArrays, iShuffled);
    // force garbage collection of extra large arrays used during warmup
    WeakReference ref1 = new WeakReference<Object>(iArrays);
    WeakReference ref2 = new WeakReference<Object>(iShuffled);
    iArrays = null;
    iShuffled = null;
    while (ref1.get() != null || ref2.get() != null) {
      System.gc();
    }
    // END WARM UP PHASE.
    System.out.printf("%8s\t%8s\t%8s\t%8s%n", "L", "Alphabet", "TimeHash", "TimeSort");
    int x = 0;
    for (int L = MIN_L; L <= MAX_L; L *= 2) {
      for (int j = 0; j < alphabetSize.length; j++) {
        int R = alphabetSize[j];
        // if (R < 1) R = 1;
        iArrays = new double[N][];
        iShuffled = new double[N][];
        for (int i = 0; i < N; i++) {
          iArrays[i] = randDoubleSequence(L, R);
          iShuffled[i] = shuffleCopy(iArrays[i]);
        }
        System.out.printf("%8d\t%8d", L, R);
        long start = bean.getCurrentThreadCpuTime();
        int dSum1 = toTime(dHash, iArrays, iShuffled);
        long end = bean.getCurrentThreadCpuTime();
        double seconds = 1.0 * (end - start) / 1000000000 / N;
        System.out.printf("\t%8.6f", seconds);
        start = bean.getCurrentThreadCpuTime();
        int dSum2 = toTime(dSort, iArrays, iShuffled);
        end = bean.getCurrentThreadCpuTime();
        seconds = 1.0 * (end - start) / 1000000000 / N;
        System.out.printf("\t%8.6f", seconds);
        System.out.println();
        // Do something with the return values to trick Java JIT.
        x += (dSum1 - dSum2);
      }
    }
    System.out.println("Done " + x);
  }

  private static double[] shuffleCopy(double[] s) {
    double[] r = s.clone();
    for (int i = s.length - 1; i > 0; i--) {
      int j = ThreadLocalRandom.current().nextInt(i + 1);
      if (i == j) continue;
      double temp = r[i];
      r[i] = r[j];
      r[j] = temp;
    }
    return r;
  }

  private static double[] randDoubleSequence(int length, int range) {
    double[] s = new double[length];
    for (int i = 0; i < length; i++) {
      s[i] = 1.0 * ThreadLocalRandom.current().nextInt(range);
    }
    return s;
  }
}
