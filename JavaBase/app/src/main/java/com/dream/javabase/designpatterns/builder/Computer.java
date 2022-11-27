package com.dream.javabase.designpatterns.builder;

/**
 * function: waiting for add
 *
 * @author zy
 * @since 2022/11/26
 */
public class Computer {

    private final String cpu;
    private final String ram;
    private final String rom;

    private Computer(Builder builder){
        cpu = builder.cpu;
        ram = builder.ram;
        rom = builder.rom;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getRom() {
        return rom;
    }

    public static class Builder{
        private  String cpu;
        private  String ram;
        private  String rom;


        public Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }


        public Builder ram(String ram) {
            this.ram = ram;
            return this;
        }


        public Builder rom(String rom) {
            this.rom = rom;
            return this;
        }

        public Computer build(){
            return new Computer(this);
        }
    }

    public static void main(String[] args) {
        final Computer computer = new Computer.Builder()
                .cpu("英特尔")
                .ram("8G")
                .rom("128G")
                .build();

        System.out.println(computer.getCpu());
        System.out.println(computer.getRam());
        System.out.println(computer.getRom());
    }
}
