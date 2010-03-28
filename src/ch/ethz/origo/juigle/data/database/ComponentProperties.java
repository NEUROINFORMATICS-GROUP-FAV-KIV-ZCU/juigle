package ch.ethz.origo.juigle.data.database;

/**
 * Extended component properties
 * 
 * Additional information for creation of components
 */
public class ComponentProperties {
  
  /** wrapped table, columns; E.g. Firebird 1.5 version **/
  private String wrappedCommands = ""; // default

  /**
   * Get wrapped command syntax:
   * "table1", "column1"
   * 
   * @return wrapped command String
   */
  public String getWrappedCommands() {
    return wrappedCommands;
  }

  /**
   * Set Wrapped prefix and suffix
   * 
   * @param wrappedCommands prefix/suffix String
   */
  public void setWrappedCommands(String wrappedCommands) {
    this.wrappedCommands = wrappedCommands;
  }
  
  
  // add next Extended Properties here...
  
}