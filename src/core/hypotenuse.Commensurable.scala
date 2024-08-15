/*
    Hypotenuse, version [unreleased]. Copyright 2024 Jon Pretty, Propensive OÜ.

    The primary distribution site is: https://propensive.com/

    Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
    file except in compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software distributed under the
    License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
    either express or implied. See the License for the specific language governing permissions
    and limitations under the License.
*/

package hypotenuse

import language.experimental.captureChecking

import scala.annotation.*

import denominative.*
import rudiments.*

object Commensurable:
  given ByteSize is Orderable as orderable:
    inline def compare
        (inline left: ByteSize, inline right: ByteSize, inline strict: Boolean, inline greater: Boolean)
            : Boolean =

      !strict && left.long == right.long || (left.long < right.long) ^ greater

  inline given Countback is Orderable:
    inline def compare
        (inline left:    Countback,
         inline right:   Countback,
         inline strict:  Boolean,
         inline greater: Boolean)
            : Boolean =

      inline if greater then inline if strict then left.gt(right) else left.ge(right)
      else inline if strict then left.lt(right) else left.le(right)

  inline given Ordinal is Orderable:
    inline def compare
        (inline left:    Ordinal,
         inline right:   Ordinal,
         inline strict:  Boolean,
         inline greater: Boolean)
            : Boolean =

      inline if greater then inline if strict then left.gt(right) else left.ge(right)
      else inline if strict then left.lt(right) else left.le(right)

trait Commensurable:
  type Self
  type Operand

  inline def compare
      (inline left: Self, inline right: Operand, inline strict: Boolean, inline greaterThan: Boolean)
          : Boolean