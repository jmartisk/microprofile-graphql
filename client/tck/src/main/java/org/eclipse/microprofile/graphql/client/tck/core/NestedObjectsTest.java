/*
 * Copyright (c) 2020 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eclipse.microprofile.graphql.client.tck.core;


import org.eclipse.microprofile.graphql.client.core.Document;
import org.eclipse.microprofile.graphql.client.core.InputObject;
import org.eclipse.microprofile.graphql.client.tck.helper.AssertGraphQL;
import org.eclipse.microprofile.graphql.client.tck.helper.Utils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.eclipse.microprofile.graphql.client.core.Argument.arg;
import static org.eclipse.microprofile.graphql.client.core.Argument.args;
import static org.eclipse.microprofile.graphql.client.core.Document.document;
import static org.eclipse.microprofile.graphql.client.core.Field.field;
import static org.eclipse.microprofile.graphql.client.core.InputObject.inputObject;
import static org.eclipse.microprofile.graphql.client.core.InputObjectField.prop;
import static org.eclipse.microprofile.graphql.client.core.Operation.operation;
import static org.eclipse.microprofile.graphql.client.core.OperationType.MUTATION;

public class NestedObjectsTest {

    @Test
    public void nestedObjectsTest() throws IOException, URISyntaxException {
        String expectedRequest = Utils.getResourceFileContent("core/nestedObjects.graphql");

        InputObject baseObject0 = inputObject(
                prop("level", 0),
                prop("name", "level 0"),
                prop("levelLineage", new byte[]{}),
                prop("nestedObjectLineage", new InputObject[]{})
        );
        InputObject baseObject1 = inputObject(
                prop("level", 1),
                prop("name", "level 1"),
                prop("levelLineage", new byte[]{0}),
                prop("nestedObjectLineage", new InputObject[]{baseObject0})
        );
        InputObject baseObject2 = inputObject(
                prop("level", 2),
                prop("name", "level 2"),
                prop("levelLineage", new byte[]{0, 1}),
                prop("nestedObjectLineage", new InputObject[]{baseObject0, baseObject1})
        );
        InputObject baseObject3 = inputObject(
                prop("level", 3),
                prop("name", "level 3"),
                prop("levelLineage", new byte[]{0, 1, 2}),
                prop("nestedObjectLineage", new InputObject[]{baseObject0, baseObject1, baseObject2})
        );

        /*
        We use cloning hereafter to avoid circular references.
         */
        InputObject object3 = inputObject();
        object3.setInputObjectFields(new ArrayList<>(baseObject3.getInputObjectFields()));
        object3.getInputObjectFields().add(prop("nestedObject", null));

        InputObject object2 = inputObject();
        object2.setInputObjectFields(new ArrayList<>(baseObject2.getInputObjectFields()));
        object2.getInputObjectFields().add(prop("nestedObject", object3));

        InputObject object1 = inputObject();
        object1.setInputObjectFields(new ArrayList<>(baseObject1.getInputObjectFields()));
        object1.getInputObjectFields().add(prop("nestedObject", object2));

        InputObject object0 = inputObject();
        object0.setInputObjectFields(new ArrayList<>(baseObject0.getInputObjectFields()));
        object0.getInputObjectFields().add(prop("nestedObject", object1));

        Document document = document(
                operation(MUTATION, "nestedObjects",
                        field("nestedObjectHolder", args(
                                arg("nestedObjectHolder", object0)),
                                field("level"),
                                field("name"),
                                field("levelLineage"),
                                field("nestedObjectLineage",
                                        field("level"),
                                        field("name"),
                                        field("levelLineage"),
                                        field("nestedObjectLineage",
                                                field("level"),
                                                field("name"),
                                                field("levelLineage"),
                                                field("nestedObjectLineage",
                                                        field("level"),
                                                        field("name"),
                                                        field("levelLineage"),
                                                        field("nestedObjectLineage",
                                                                field("level"),
                                                                field("name"),
                                                                field("levelLineage"))))),
                                field("nestedObject",
                                        field("level"),
                                        field("name"),
                                        field("levelLineage"),
                                        field("nestedObjectLineage",
                                                field("level"),
                                                field("name"),
                                                field("levelLineage"),
                                                field("nestedObjectLineage",
                                                        field("level"),
                                                        field("name"),
                                                        field("levelLineage"),
                                                        field("nestedObjectLineage",
                                                                field("level"),
                                                                field("name"),
                                                                field("levelLineage"),
                                                                field("nestedObjectLineage",
                                                                        field("level"),
                                                                        field("name"),
                                                                        field("levelLineage"))))),
                                        field("nestedObject",
                                                field("level"),
                                                field("name"),
                                                field("levelLineage"),
                                                field("nestedObjectLineage",
                                                        field("level"),
                                                        field("name"),
                                                        field("levelLineage"),
                                                        field("nestedObjectLineage",
                                                                field("level"),
                                                                field("name"),
                                                                field("levelLineage"),
                                                                field("nestedObjectLineage",
                                                                        field("level"),
                                                                        field("name"),
                                                                        field("levelLineage"),
                                                                        field("nestedObjectLineage",
                                                                                field("level"),
                                                                                field("name"),
                                                                                field("levelLineage"))))),
                                                field("nestedObject",
                                                        field("level"),
                                                        field("name"),
                                                        field("levelLineage"),
                                                        field("nestedObjectLineage",
                                                                field("level"),
                                                                field("name"),
                                                                field("levelLineage"),
                                                                field("nestedObjectLineage",
                                                                        field("level"),
                                                                        field("name"),
                                                                        field("levelLineage"),
                                                                        field("nestedObjectLineage",
                                                                                field("level"),
                                                                                field("name"),
                                                                                field("levelLineage"),
                                                                                field("nestedObjectLineage",
                                                                                        field("level"),
                                                                                        field("name"),
                                                                                        field("levelLineage"))))),
                                                        field("nestedObject",
                                                                field("level"),
                                                                field("name"),
                                                                field("levelLineage"),
                                                                field("nestedObjectLineage",
                                                                        field("level"),
                                                                        field("name"),
                                                                        field("levelLineage"),
                                                                        field("nestedObjectLineage",
                                                                                field("level"),
                                                                                field("name"),
                                                                                field("levelLineage"),
                                                                                field("nestedObjectLineage",
                                                                                        field("level"),
                                                                                        field("name"),
                                                                                        field("levelLineage"),
                                                                                        field("nestedObjectLineage",
                                                                                                field("level"),
                                                                                                field("name"),
                                                                                                field("levelLineage"))))),
                                                                field("nestedObject",
                                                                        field("level"),
                                                                        field("name"),
                                                                        field("levelLineage"),
                                                                        field("nestedObjectLineage",
                                                                                field("level"),
                                                                                field("name"),
                                                                                field("levelLineage"),
                                                                                field("nestedObjectLineage",
                                                                                        field("level"),
                                                                                        field("name"),
                                                                                        field("levelLineage"),
                                                                                        field("nestedObjectLineage",
                                                                                                field("level"),
                                                                                                field("name"),
                                                                                                field("levelLineage"),
                                                                                                field("nestedObjectLineage",
                                                                                                        field("level"),
                                                                                                        field("name"),
                                                                                                        field("levelLineage")))))))))))));

        String generatedRequest = document.build();
        //System.out.println(generatedRequest);
        AssertGraphQL.assertEquivalentGraphQLRequest(expectedRequest, generatedRequest);
    }
}
