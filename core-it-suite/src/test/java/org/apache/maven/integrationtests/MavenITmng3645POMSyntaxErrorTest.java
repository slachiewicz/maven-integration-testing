package org.apache.maven.integrationtests;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.it.VerificationException;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

/**
 * This is a test set for <a href="http://jira.codehaus.org/browse/MNG-3645">MNG-3645</a>.
 *
 * @todo Fill in a better description of what this test verifies!
 *
 * @author <a href="mailto:brianf@apache.org">Brian Fox</a>
 * @author jdcasey
 *
 */
public class MavenITmng3645POMSyntaxErrorTest
    extends AbstractMavenIntegrationTestCase
{
    public MavenITmng3645POMSyntaxErrorTest()
    {
        super( "(2.0.10,)" ); // only test in 2.0.10+
    }

    public void testitMNG3645 ()
        throws Exception
    {
        // The testdir is computed from the location of this
        // file.
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/mng-3645-pomSyntaxError" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );

        try
        {
            verifier.executeGoal( "validate" );

            fail( "Should fail to validate the POM syntax due to missing dependency element inside dependencyManagement section." );
        }
        catch ( VerificationException e )
        {
            // expect this.
        }
    }
}