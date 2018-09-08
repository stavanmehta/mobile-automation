package automation;

import java.io.IOException;

public interface TestTemplate {
	
	public void globalSetup () throws IOException;

    public void globalTearDown (); 

}
