package RegFile;

import chisel3._

import ArchConfig._


class RegIO (arch: ArchConfig) extends Bundle {
  val read_addr0 = Input(UInt(arch.regAddrWidth.W))
  val read_addr1 = Input(UInt(arch.regAddrWidth.W))

  val write_addr = Input(UInt(arch.regAddrWidth.W))
  val write_data = Input(UInt(arch.regAddrWidth.W))
  val write_enable = Input(Bool())
  val borrow_enable = Input(Bool())

  val owner_except = Output(Bool())
  val read0 = Output(UInt(arch.xLen.W))
  val read1 = Output(UInt(arch.xLen.W))
}



class RegFile (arch: ArchConfig) extends Module {
  val io = IO(new RegIO(arch))
  val regfile = Reg(Vec(arch.regFileSize, UInt(arch.xLen.W)))
  val borrow_flags = Reg(Vec(arch.regFileSize, Bool()))

  io.read0 := Mux(io.read_addr0 === 0.U, 0.U, regfile(io.read_addr0))
  io.read1 := Mux(io.read_addr1 === 0.U, 0.U, regfile(io.read_addr1))

  io.owner_except := borrow_flags(io.read_addr0) || borrow_flags(io.read_addr1)

  when(io.write_addr =/= 0.U) {
    when(io.write_enable) {
      regfile(io.write_addr) := io.write_data
    }.otherwise {
      borrow_flags(io.write_addr) := io.borrow_enable
    }
  }
}