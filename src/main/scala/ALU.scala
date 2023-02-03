

package ALU;

import chisel3._

import ArchConfig._
import RegFile._


class ALUException extends Bundle {
  val owner_except = Output(Bool())
  val div_zero = Output(Bool())
}


class ALU (arch: ArchConfig) extends Module {
  val io = IO(new Bundle {
    val aluop     = Input(UInt(5.W))
    val op2_is_imm= Input(Bool())
    val dest      = Input(UInt(arch.regAddrWidth.W))
    val op1       = Input(UInt(arch.regAddrWidth.W))
    val op2       = Input(UInt(14.W))

    val reg   = Flipped(new RegIO(arch))

    val output_data = Output(UInt(arch.xLen.W))
    val exception = Output(UInt(1.W))
  })

  io.reg.read_addr0 := io.op1
  io.reg.read_addr1 := Mux(io.op2_is_imm, 0.U, io.op2)

  io.reg.write_addr := io.dest
  io.reg.borrow_enable := true.B

  val data0 = io.reg.read0
  val data1 = Mux(io.op2_is_imm, io.op2, io.reg.read1)

  io.output_data := data0 + data1
  // MuxLookup(aluop, {

  // })
}
