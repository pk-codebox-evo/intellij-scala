package org.jetbrains.plugins.scala
package lang
package psi
package stubs
package elements

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.stubs._
import com.intellij.util.io.StringRef
import org.jetbrains.plugins.scala.lang.psi.api.base.ScFieldId
import org.jetbrains.plugins.scala.lang.psi.impl.base.ScFieldIdImpl
import org.jetbrains.plugins.scala.lang.psi.stubs.impl.ScFieldIdStubImpl

/**
  * User: Alexander Podkhalyuzin
  * Date: 19.07.2009
  */
class ScFieldIdElementType[Func <: ScFieldId]
  extends ScStubElementType[ScFieldIdStub, ScFieldId]("field id") {
  override def serialize(stub: ScFieldIdStub, dataStream: StubOutputStream): Unit = {
    dataStream.writeName(stub.getName)
  }

  override def deserialize(dataStream: StubInputStream, parentStub: StubElement[_ <: PsiElement]): ScFieldIdStub =
    new ScFieldIdStubImpl(parentStub, this, StringRef.toString(dataStream.readName))

  override def createStub(psi: ScFieldId, parentStub: StubElement[_ <: PsiElement]): ScFieldIdStub =
    new ScFieldIdStubImpl(parentStub, this, psi.name)

  override def createElement(node: ASTNode): ScFieldId = new ScFieldIdImpl(node)

  override def createPsi(stub: ScFieldIdStub): ScFieldId = new ScFieldIdImpl(stub)
}