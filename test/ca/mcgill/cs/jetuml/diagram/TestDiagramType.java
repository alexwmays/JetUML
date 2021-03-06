/*******************************************************************************
 * JetUML - A desktop application for fast UML diagramming.
 *
 * Copyright (C) 2018, 2019 by the contributors of the JetUML project.
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
package ca.mcgill.cs.jetuml.diagram;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ca.mcgill.cs.jetuml.JavaFXLoader;
import ca.mcgill.cs.jetuml.diagram.builder.ClassDiagramBuilder;
import ca.mcgill.cs.jetuml.diagram.builder.ObjectDiagramBuilder;
import ca.mcgill.cs.jetuml.diagram.builder.SequenceDiagramBuilder;
import ca.mcgill.cs.jetuml.diagram.builder.StateDiagramBuilder;
import ca.mcgill.cs.jetuml.diagram.builder.UseCaseDiagramBuilder;

public class TestDiagramType
{
	@BeforeAll
	public static void setupClass()
	{
		JavaFXLoader.load();
	}
	
	@Test
	public void testnewInstance()
	{
		assertSame(ClassDiagram.class, DiagramType.CLASS.newInstance().getClass());
		assertSame(ObjectDiagram.class, DiagramType.OBJECT.newInstance().getClass());
		assertSame(SequenceDiagram.class, DiagramType.SEQUENCE.newInstance().getClass());
		assertSame(StateDiagram.class, DiagramType.STATE.newInstance().getClass());
		assertSame(UseCaseDiagram.class, DiagramType.USECASE.newInstance().getClass());
	}
	
	@Test
	public void testTypeOf()
	{
		assertSame(DiagramType.CLASS, DiagramType.typeOf(new ClassDiagram()));
		assertSame(DiagramType.OBJECT, DiagramType.typeOf(new ObjectDiagram()));
		assertSame(DiagramType.SEQUENCE, DiagramType.typeOf(new SequenceDiagram()));
		assertSame(DiagramType.STATE, DiagramType.typeOf(new StateDiagram()));
		assertSame(DiagramType.USECASE, DiagramType.typeOf(new UseCaseDiagram()));
	}
	
	@Test
	public void testNewBuilderInstance()
	{
		assertSame(ClassDiagramBuilder.class, DiagramType.newBuilderInstanceFor(new ClassDiagram()).getClass());
		assertSame(ObjectDiagramBuilder.class, DiagramType.newBuilderInstanceFor(new ObjectDiagram()).getClass());
		assertSame(SequenceDiagramBuilder.class, DiagramType.newBuilderInstanceFor(new SequenceDiagram()).getClass());
		assertSame(StateDiagramBuilder.class, DiagramType.newBuilderInstanceFor(new StateDiagram()).getClass());
		assertSame(UseCaseDiagramBuilder.class, DiagramType.newBuilderInstanceFor(new UseCaseDiagram()).getClass());
	}
}
