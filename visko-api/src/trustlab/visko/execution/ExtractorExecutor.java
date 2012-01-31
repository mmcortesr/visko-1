// The MIT License
//
// Copyright (c) 2004 Evren Sirin
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to
// deal in the Software without restriction, including without limitation the
// rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
// sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
// IN THE SOFTWARE.

/*
 * Created on Mar 19, 2004
 */
package trustlab.visko.execution;

import org.mindswap.owl.OWLDataValue;
import org.mindswap.owl.OWLFactory;
import org.mindswap.owl.OWLKnowledgeBase;
import org.mindswap.owl.OWLValue;
import org.mindswap.owls.OWLSFactory;
import org.mindswap.owls.process.Process;
import org.mindswap.owls.process.execution.ProcessExecutionEngine;
import org.mindswap.owls.process.variable.Input;
import org.mindswap.owls.process.variable.Output;
import org.mindswap.owls.service.Service;
import org.mindswap.query.ValueMap;

import trustlab.visko.ontology.service.OWLSService;

/**
 * 
 * Examples to show how services can be executed. Some examples of simple
 * execution monitoring is included.
 * 
 * @author unascribed
 * @version $Rev: 2298 $; $Author: nick $; $Date: 2012/01/30 20:27:47 $
 */
public class ExtractorExecutor {
	private Service service;
	private Process process;
	private ValueMap<Output, OWLValue> outputs;
	private ProcessExecutionEngine exec;

	private OWLSService extractorService;

	public ExtractorExecutor(OWLSService extractorService) {
		this.extractorService = extractorService;
	}

	public String executeExtractor(String datasetURL) throws Exception {
		// create an execution engine
		exec = OWLSFactory.createExecutionEngine();

		// Attach a listener to the execution engine
		// exec.addMonitor(new DefaultProcessMonitor());

		OWLKnowledgeBase kb = OWLFactory.createKB();

		service = extractorService.getIndividual();
		process = service.getProcess();

		// initialize the input values to be empty
		ValueMap<Input, OWLValue> inputs = new ValueMap<Input, OWLValue>();
		inputs.setValue(process.getInput(), kb.createDataValue(datasetURL));

		outputs = exec.execute(process, inputs, kb);

		// get the output
		final OWLDataValue out = (OWLDataValue) outputs.getValue(process
				.getOutput());

		// display the results
		// System.out.println("Executed service '" + service + "'");
		// System.out.println("Grounding WSDL: " + ((AtomicProcess)
		// process).getGrounding().getDescriptionURL());
		// System.out.println("Input  = " + inValue);
		// System.out.println("Output URL: " + out.toString());

		return out.toString();
	}
}