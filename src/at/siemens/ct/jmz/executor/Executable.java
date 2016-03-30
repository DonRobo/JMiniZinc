package at.siemens.ct.jmz.executor;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single executable that can be executed via a system call.
 * 
 * @author z003ft4a (Richard Taupe)
 *
 */
public interface Executable {

  /**
   * @return the name of the executable.
   */
  String getName();

  /**
   * @see #getOptions(Long, Collection)
   * @return the list of options, including neither a time limit nor search directories.
   */
  default List<String> getOptions() {
    return getOptions(null, Collections.emptyList());
  }

  /**
   * Returns a list of options, including specified timeout and search directories only if the underlying executable
   * supports them.
   * 
   * @param timeoutMs
   *          a time limit in milliseconds
   * @param searchDirectories
   *          paths where to search for included files
   * @return the list of options, including the given time limit (if {@code timeoutMs != null} and {@code timeoutMs > 0})
   *         and the given search directories (if supported by the executable).
   */
  List<String> getOptions(Long timeoutMs, Collection<Path> searchDirectories);

}
