/**
 * Copyright � Siemens AG, 2016
 * 
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package at.siemens.ct.jmz.executor;

import java.io.IOException;

/**
 * @author � Siemens AG, 2016
 */
public interface IExecutor {

  static final int EXIT_CODE_SUCCESS = 0;

  /**
   * Calls {@link #startProcess(Long)} without a time limit.
   */
  default void startProcess(String... additionaloptions) throws IOException {
    startProcess(null,additionaloptions);
  }

  /**
   * Starts the process as configured and lets it read the model from a file generated by the model writer.<br>
   * If {@code timeoutMs != null}, calculation will be forcefully stopped after the given number of milliseconds. If the
   * underlying solver supports it, it will additionally be asked to voluntarily terminate 1 second before that. Note
   * that child processes spawned by the underlying solver cannot be killed.
   * 
   * @param timeoutMs
   *          a time limit in milliseconds.
   * @param additionaloptions
   *          additional options for executable
   * @throws IOException
   *           if a process executable cannot be found.
   */
  void startProcess(Long timeoutMs,String... additionaloptions) throws IOException;  
  
  /**
   * Waits until the running process returns with a solution.
   * 
   * @return the time that has elapsed since {@link #startProcess()} (in milliseconds).
   * @throws IOException
   *           if a process executable cannot be found.
   */
  long waitForSolution() throws InterruptedException, IOException;

  /**
   * @return the output generated during the last solver run.
   */
  String getLastSolverOutput();

  /**
   * @return the error messages generated during the last solver run.
   */
  String getLastSolverErrors();

  /**
   * Prints {@link #getLastSolverErrors()} to {@link System#err}.
   */
  default void printLastSolverErrors() {
    String lastSolverErrors = getLastSolverErrors();
    if (!lastSolverErrors.isEmpty()) {
      System.err.println(lastSolverErrors);
    }
  }

  /**
   * @see Process#exitValue()
   * @return the exit value of the {@link Process} that terminated last. By convention, the value 0 indicates normal
   *         termination.
   */
  int getLastExitCode();

}