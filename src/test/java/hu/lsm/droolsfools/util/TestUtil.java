package hu.lsm.droolsfools.util;

import hu.lsm.droolsfools.entity.EEARule;

public class TestUtil {

    public static final String HELLO_WORLD_DRL = "import hu.lsm.droolsfools.dto.IncomingData;\n" +
            "\n" +
            "rule \"Hello World\"\n" +
            "      dialect \"mvel\"\n" +
            "  when\n" +
            "    m : IncomingData( errorCode == 200, value == \"SERVER RESPONSE\", message : message )\n" +
            "  then\n" +
            "\n" +
            "    System.out.println( message );\n" +
            "    //modify ( m ) { message = \"OK\" };\n" +
            "    m.setMessage(\"OK\");\n" +
            "    System.out.println( message );\n" +
            "end";

    public static EEARule getEEARule(){
        EEARule eeaRule = new EEARule();
        eeaRule.setId(1L);
        eeaRule.setName("Test rule");
        return eeaRule;
    }

}
