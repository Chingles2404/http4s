/*
 * Copyright 2013 http4s.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.http4s
package headers

import org.http4s.util.{CaseInsensitiveString, Writer}
import cats.data.NonEmptyList
import org.http4s.parser.HttpHeaderParser

object Upgrade extends HeaderKey.Internal[Upgrade] with HeaderKey.Recurring {
  override def parse(s: String): ParseResult[Upgrade] =
    HttpHeaderParser.UPGRADE(s)
}

final case class Upgrade(values: NonEmptyList[CaseInsensitiveString]) extends Header.Recurring {
  override def key: Upgrade.type = Upgrade
  type Value = CaseInsensitiveString
  override def renderValue(writer: Writer): writer.type =
    writer.addStringNel(values.map(_.toString), ", ")
}
