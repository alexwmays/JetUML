/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2016, 2019 by the contributors of the JetUML project.
 *
 * See: https://github.com/prmr/JetUML
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package ca.mcgill.cs.jetuml.viewers.nodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ca.mcgill.cs.jetuml.JavaFXLoader;
import ca.mcgill.cs.jetuml.diagram.ObjectDiagram;
import ca.mcgill.cs.jetuml.diagram.nodes.FieldNode;
import ca.mcgill.cs.jetuml.diagram.nodes.ObjectNode;
import ca.mcgill.cs.jetuml.geom.Direction;
import ca.mcgill.cs.jetuml.geom.Point;
import ca.mcgill.cs.jetuml.geom.Rectangle;
import ca.mcgill.cs.jetuml.viewers.nodes.FieldNodeViewer;
import ca.mcgill.cs.jetuml.viewers.nodes.NodeViewerRegistry;

public class TestFieldNodeViewer
{
	private ObjectNode aObjectNode1;
	private FieldNode aFieldNode1;
	private Graphics2D aGraphics;
	private ObjectDiagram aDiagram;
	private FieldNodeViewer aFieldNodeViewer = new FieldNodeViewer();
	
	@BeforeAll
	public static void setupClass()
	{
		JavaFXLoader.load();
	}
	
	@BeforeEach
	public void setup()
	{
		aObjectNode1 = new ObjectNode();
		aFieldNode1 = new FieldNode();
		aGraphics = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB).createGraphics();
		aDiagram = new ObjectDiagram();
		aDiagram.addRootNode(aObjectNode1);
	}
	
	@Test
	public void testDimensionsUnattachedWithNameString()
	{
		aFieldNode1.setName("XXXXX");
		assertEquals(47, aFieldNodeViewer.leftWidth(aFieldNode1));    // The length of the string
		assertEquals(36, aFieldNodeViewer.rightWidth(aFieldNode1));   // Half the default width + mid offset.
		assertEquals(22, aFieldNodeViewer.getHeight(aFieldNode1));    // The height of the string
	}
	
	@Test
	public void testDimensionsUnattachedWithValueString()
	{
		aFieldNode1.setValue("XXXXX");
		assertEquals(6, aFieldNodeViewer.leftWidth(aFieldNode1));    	// Just the length of the mid offset
		assertEquals(47, aFieldNodeViewer.rightWidth(aFieldNode1));  	// The length of the string
		assertEquals(22, aFieldNodeViewer.getHeight(aFieldNode1));    	// The height of the string
	}
	
	@Test
	public void testGetBoundsUnattachedNoStrings()
	{
		// x = axis (30) - offset (6)
		// y = 0
		// w = default length (30)/2 + 2* offset (6) = 42
		// h = default height = 20
		assertEquals( new Rectangle(24,0,42,20), NodeViewerRegistry.getBounds(aFieldNode1));
	}
	
	@Test
	public void testGetBoundsUnattachedNameValueString()
	{
		aFieldNode1.setName("XXXXX");
		aFieldNode1.setValue("XXXXX");
		// x = axis (30) - offset + length (47)  = -17
		// y = 0
		// w = 47 * 2
		// h = text height 22
		assertEquals( new Rectangle(-17,0,94,22), NodeViewerRegistry.getBounds(aFieldNode1));
	}
	
	@Test
	public void testGetConnectionPointsUnattached()
	{
		// x = max x of the node bounds - x gap
		// y = half-point of the default height
		assertEquals( new Point(61,10), NodeViewerRegistry.getConnectionPoints(aFieldNode1, Direction.EAST));
	}
	
	// NEW
	
	@Test
	public void testDimensionsAttachedNoStrings()
	{
		aObjectNode1.addChild(aFieldNode1);
		assertEquals(6, aFieldNodeViewer.leftWidth(aFieldNode1));    // Just the length of the mid offset
		assertEquals(36, aFieldNodeViewer.rightWidth(aFieldNode1));  // Half the default width + mid offset.
		assertEquals(20, aFieldNodeViewer.getHeight(aFieldNode1));   // Default height
	}
	
	@Test
	public void testDimensionsAttachedObjectString()
	{
		aObjectNode1.addChild(aFieldNode1);
		aObjectNode1.setName("XXXXXXXXXXXXXXXXXXX");
		assertEquals(6, aFieldNodeViewer.leftWidth(aFieldNode1));    // Just the length of the mid offset
		assertEquals(36, aFieldNodeViewer.rightWidth(aFieldNode1));  // Half the default width + mid offset.
		assertEquals(20, aFieldNodeViewer.getHeight(aFieldNode1));   // Default height
	}
	
	@Test
	public void testDimensionsAttachedWithNameString()
	{
		aObjectNode1.addChild(aFieldNode1);
		aFieldNode1.setName("XXXXX");
		assertEquals(47, aFieldNodeViewer.leftWidth(aFieldNode1));    // The length of the string
		assertEquals(36, aFieldNodeViewer.rightWidth(aFieldNode1));   // Half the default width + mid offset.
		assertEquals(22, aFieldNodeViewer.getHeight(aFieldNode1));    // The height of the string
	}
	
	@Test
	public void testDimensionsAttachedWithValueString()
	{
		aObjectNode1.addChild(aFieldNode1);
		aFieldNode1.setValue("XXXXX");
		assertEquals(6, aFieldNodeViewer.leftWidth(aFieldNode1));    	// Just the length of the mid offset
		assertEquals(47, aFieldNodeViewer.rightWidth(aFieldNode1));  	// The length of the string
		assertEquals(22, aFieldNodeViewer.getHeight(aFieldNode1));    	// The height of the string
	}
	
	@Test
	public void testGetBoundsAttachedNoStrings()
	{
		aObjectNode1.addChild(aFieldNode1);
		// x = axis (45) - offset (6) = 39
		// y = top node height
		// w = left + right
		// h = default height
		assertEquals( new Rectangle(5,70,70,20), NodeViewerRegistry.getBounds(aFieldNode1));
	}
	
	@AfterEach
	public void teardown()
	{
		aGraphics.dispose();
	}
}
