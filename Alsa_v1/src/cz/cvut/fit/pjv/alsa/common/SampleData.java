package cz.cvut.fit.pjv.alsa.common;

import cz.cvut.fit.pjv.alsa.common.entity.Notebook;
import cz.cvut.fit.pjv.alsa.common.entity.NotebookCategory;
import cz.cvut.fit.pjv.alsa.common.entity.Television;
import cz.cvut.fit.pjv.alsa.common.entity.part.ComputerPart;
import cz.cvut.fit.pjv.alsa.common.entity.part.Memory;
import cz.cvut.fit.pjv.alsa.common.entity.part.Processor;
import cz.cvut.fit.pjv.alsa.common.entity.part.ProcessorSpeed;

public class SampleData {

    public final static Processor intel = new Processor(ProcessorSpeed.FAST);
    public final static Processor amd = new Processor(ProcessorSpeed.FAST);

    public final static Memory memory200GB = new Memory(200);
    public final static Memory memory500GB = new Memory(500);

    public final static Notebook lenovoE500 = new Notebook(1, "Lenovo E500", 10000, 2, NotebookCategory.BASIC, new ComputerPart[]{intel, memory200GB});
    public final static Notebook hpBusinnesPlus = new Notebook(2, "HP Business Plus", 40000, 5, NotebookCategory.PROFESSIONAL, new ComputerPart[]{amd, memory500GB});

    public final static Television samsungMediaPlus = new Television(3, "Samsung Media Plus", 5000, 2);

}
