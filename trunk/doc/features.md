
# selogger �̋@�\

selogger �́CJava �v���O�����̎��s������ *����Ȃ��* �ڂ����L�^���邱�Ƃ�ڎw�����c�[���ł��D


## �g�p���@

�����������: ��͑Ώۃv���O�����ƁC���̃v���O���������s���邽�߂̓��̓f�[�^�����K�v�ł��D


1. ��͑Ώۃv���O�����i�o�C�i���j�̃o�C�g�R�[�h�ϊ������s�C�Ώۃv���O�����Ɋϑ��p���߂𖄂ߍ���ł��������D
    * ���s�ɕK�v�ȃN���X:  selogger �̃N���X�Q�� lib �f�B���N�g���̂��ׂĂ� JAR �t�@�C���� classpath �ɐݒ肵�Ă��������Dmain �N���X�� selogger.weaver.TraceWeaver �ł��D
    * �����Ƃ��āC��͑Ώۃv���O�����̃N���X�t�@�C���������Ă���f�B���N�g���C���邢�� JAR �t�@�C���C�N���X�t�@�C�����w�肵�܂��D�����w�肷�邱�Ƃ��ł��܂��D
    * �w�肵���f�B���N�g���Ɋi�[����Ă��� JAR �t�@�C������͑ΏۂƂ���ꍇ�� -jardir �I�v�V������t���Ă��������i�w�肵�Ȃ��ƃf�B���N�g�������̃N���X�t�@�C���������ΏۂɂȂ�܂��j�D
    * JAR �t�@�C���̒��ɓ����Ă��� JAR �t�@�C������͑ΏۂƂ���ꍇ�� -innerJAR �I�v�V������t���Ă��������DDaCapo�x���`�}�[�N�Ȃǂ𑊎�ɂ���Ƃ��ɕK�v�ł��D
    * �o�C�g�R�[�h�ϊ��̌��ʂ�ϊ��r���̃G���[���O�C��͎��Ɏg�p����f�[�^�t�@�C���������o���f�B���N�g���� -output=path/to/dir �I�v�V�����Ŏw�肵�܂��D�w�肵�Ȃ��ƃJ�����g�f�B���N�g���ɏo�͂���܂��D
    * JDK 1.6 �ȑO�̃t�@�C����ΏۂƂ��Ă���Ƃ��� -jdk16 ���w�肵�Ă��������D�w�肵�Y���ƁC�v���O�������s�̒i�K�� VerifyError �����o����܂��D
    * �G���[�ł���~���Ȃ��悤�ɂ������ꍇ�� -ignoreError ���w�肵�܂��D���̃I�v�V�������w�肷��ƁC�ϊ����ɃG���[���N�����N���X�͕ϊ������R�s�[�����悤�ɂȂ�܂��DDaCapo�x���`�}�[�N�̂悤�ɁC��̓G���[���N����t�@�C�����Ӑ}�I�Ɋi�[���Ă���JAR�t�@�C���������ꍇ�ɕK�v�ł��D
    * �擾�������C�x���g�̎�ނ�ύX�������ꍇ�� -weave= �I�v�V�����Ŏw�肵�Ă��������D-weave=ALL �Ǝw�肷��Ƃ��ׂĂ̖��߂��ϑ����܂��DEXEC,CALL,ARRAY,FILED,MISC,LABEL��6��ނ��J���}�ŋ�؂��Ďw��ł��C���\�b�h�̎��s�C�Ăяo���C�z��̑���C�t�B�[���h�A�N�Z�X�C���̑��̃I�u�W�F�N�g���얽�߁iMONITOR��INSTANCEOF�j�C�������򓙂ɂ�郉�x���ʉ߂��L�^�ł��܂��D�f�t�H���g�̓��x���ȊO�̂��ׂĂł��D
    * �f�o�b�O�p: �ϊ���̃o�C�g�R�[�h���� -verify �I�v�V�����ŏo�͂ł��܂��D�e���\�b�h�̂��ׂĂ̓��e���o�͂��邽�߁C��K�̓v���O�����ɓK�p����ƃf�B�X�N�e�ʂ���������\��������܂��D
    
2. �ϊ����ꂽ��͑Ώۃv���O�������Cselogger/bin �f�B���N�g���ȉ��̃N���X�Q�� classpath �Ɋ܂߂���ԂŎ��s���Ă��������ilib�f�B���N�g���̑��̃N���X�ɂ͈ˑ����܂���j�D-output �Ŏw�肵���f�B���N�g���� weave ���ʂ� JAR ��N���X�t�@�C�����o�͂����̂ŁC�N���X�p�X�ɂ����̃N���X�� selogger �̃N���X�Q���w�肵����ԂőΏۃv���O���������s���Ă��������D���̂Ƃ��L����VM�I�v�V�����͎��̒ʂ�ł��D
    * -Dselogger.dir=�f�B���N�g����.  ���O�������o���f�B���N�g�����w�肵�Ă��������D
    * -Dselogger.threads=�X���b�h���D���O���o�b�N�O���E���h�Ńf�B�X�N�ɏ����o���X���b�h���D1������RAM��400MB���x�K�v�D�f�t�H���g��0�DSSD��CPU�ɂ��]�T������΂�4�`6���x���L���ł��D
    * -Dselogger.errorlog=�t�@�C�����DI/O��O�Ȃǂ����������Ƃ��ɃG���[���O�������o���t�@�C���D�f�B�X�N���Ȃǂ̃G���[�ɂ��ẮC���O�����o����ƕʃf�B�X�N�łȂ��ƈӖ����Ȃ����Ƃ�����܂��D�w�肵�Ȃ���΂ǂ��ɂ������o���܂���D
    * -Dselogger.output=�o�͌`���D�Œ蒷�E���k����(fixed-compress)���W���Dvariable �Ƃ���Ɖϒ������k�ŁC10�{�ȏ�̃f�B�X�N�e�ʂ�����邩���RAM�f�B�X�N��ł͔��ɍ����ɂȂ�܂��Dprofile �Ǝw�肷��ƁC���O�������o�������ɃC�x���g�̏o���p�x�\���e�L�X�g�`���ŏo�͂��܂��D

3. �o�͂��ꂽ���O����͂��܂��D
    * selogger.reader.LogPrinter �N���X�� main �Ƃ��āC-dir=�f�B���N�g���� �Ƃ����`���� -Dselogger.dir �̃f�B���N�g�����w�肷��ƁC���̃��O�̓��e��P���ɕW���o�͂ɏ����o���܂��D
    * selogger.reader.LogDirectory, EventReader �N���X���g���āC�����ŉ�͂̂��߂̃v���O�������쐬���Ă��������D   



## �����ς݂̋@�\

* JAR�t�@�C���ւ� weaving �ɑΉ��D
* �L�^�ł���C�x���g
  * ���\�b�h�̎��s�J�n (EVENT_METHOD_ENTER)�C�I�� (EVENT_METHOD_NORMAL_EXIT, EVENT_METHOD_EXCEPTIONAL_EXIT)�D������ (EVENT_FORMAL_PARAM)�D
  * ���\�b�h�Ăяo�����߂̎��s (EVENT_METHOD_CALL) �Ǝ����� (ACTUAL_PARAM)�D�Ăяo������̕��A�ɑ΂��ẮC���Ƃ��߂�l�̌^�� void �ł����Ă� EVENT_RETURN_VALUE_AFTER_CALL ���L�^�����D�R���X�g���N�^�Ăяo���̏ꍇ�́C�����ɏ��������ꂽ�I�u�W�F�N�g���L�^�����iEVENT_OBJECT_CREATION_COMPLETED�j�D�R���X�g���N�^�̒��ł���ɏ�ʂ̃R���X�g���N�^���g�����ꍇ�̕����I�ȏ������̊����� EVENT_OBJECT_INITIALIZED�Ƃ��ċL�^�����D
  * �z��̓ǂݏ����C�t�B�[���h�̓ǂݏ����D�t�B�[���h�Ɣz��̓ǂݏo���ł́C�����������ƁC�ǂ݂������l�𗼕��L�^����i�A�N�Z�X���ɗ�O����������\��������̂Ōʂ̃C�x���g�Ƃ��ċL�^�j�D
* JDK 1.8 �܂ł̃o�C�g�R�[�h�ɑΉ��D
  * JDK 1.7 ����o�C�g�R�[�h�� Stack Map �Ƃ�����񂪕K�v�ɂȂ������C������Čv�Z���邽�߂ɃN���X�K�w��񂪗v�������ꍇ������H�i���m�ȗ��R�͕s���j�D
  * �N���X���s�����Ă���ꍇ�́Cweaving ���s���� -classpath �ŊY���R�[�h�ւ̃N���X�p�X��ʂ��Ǝ��s�ɐ�������D
* Weave �Ɏg�����p�����[�^�ݒ�iWeavingInfo�j�́C���ꎩ�̂��o�̓f�B���N�g���ɏ����o�����D����ł́C�ΏۃN���X�̈ꗗ�Ȃǂ͊܂܂�Ă��Ȃ��iWeaver �ɐ؂藣����Ă���j�D



## selogger �̍\���v�f

### Weave (selogger.weaver�p�b�P�[�W)

�ϑ��������Ώۂ� Java �v���O�����ɑ΂��ăo�C�g�R�[�h�ϊ������s���C���O���L�^���邽�߂̖��߂𖄂ߍ��݂܂��D
���݂̎����ł� TraceWeaver �Ƃ����N���X�ɁC�Ώۃv���O�����̏�񂪂��̂܂܃n�[�h�R�[�f�B���O����Ă��܂��D

���Ȃ�̗ʂ̖��߂�ǉ��Ŗ��ߍ��ނ��ƂɂȂ邽�߁i���Ƃ��΂P�̃��\�b�h�ďo���ɑ΂��āC�ďo�������������ƂƊ����������ƁC�����̒l�������o���ɂ����܂��j�C�o�C�g�R�[�h�̗ʂ�Java�̐����𒴂���悤�ȃv���O�����ɑ΂��Ă͊ϑ����s�����Ƃ��ł��܂���D

### ���O�̋L�^ (selogger.logging �p�b�P�[�W)

���O�����ۂɃt�@�C���ɏ����o���@�\��S������N���X�Q�ł��D
Logging �����̑�\�𖱂߂Ă���CWeaver �� Logging �N���X�ւ̌ďo���𖄂ߍ��݂܂��D

�C�x���g���e���f�[�^�t�H�[�}�b�g�ɑΉ�������d���� LogWriter ���S���Ă��܂��D

### ��� (selogger.reader �p�b�P�[�W)

���O�t�@�C����ǂ݂������̎d����S������N���X�Q�ł��D
LogDirectory �N���X����Ƃ̋N�_�ƂȂ�܂��D
FullTraceValidation �ɁC���O�̓��e���`�F�b�N����d�g�݂����삵�Ă���܂��D


### ���̑��̍\���v�f

* tests �f�B���N�g���ȉ��ɂ́C�����p�ɍ��ꂽ�������̃e�X�g�R�[�h���i�[����Ă��܂��D
  * selogger.testdata �́C�o�C�g�R�[�hWeaving�̎����Ώۂł���C�Ӑ}�I��Java�̓���̖��߂��g�������O���N�������肷�閽�߂����ߍ���ł���܂��D
* old �f�B���N�g���ɉߋ��̎���i���i�[����Ă��邪�C�����͌Â����O�`���ɑΉ��������̂ŁC���݂̃o�[�W�����ɂ͓K�p�ł��܂���D



## Weave ���s���ɏo�͂����f�[�^�t�@�C��

���O�擾�p�R�[�h�̖��ߍ��݂́C�N���X�t�@�C�����̂��j�����Ă���ꍇ��C���ߍ��񂾌��ʂƂ��đΏۃt�@�C�������ɑ傫���Ȃ��Ă��܂��ꍇ�Ɏ��s���܂��D
Weaver �͎����Ń��O�擾���ȑf�����čēx Weaving �����݂܂��D
�ŏI�I�ɑΏۂɂȂ����N���X�̃��X�g�� classes.txt �ɁC�r���Ŕ��������G���[�� log.txt �ɏ������܂��܂��D

  * classes.txt �ɂ́C�N���X�̒ʂ��ԍ��C�N���X���i�[����Ă���JAR���邢�̓f�B���N�g���C�N���X�t�@�C���̖��O�C�N���X�̖��O�CWeaving�̐��ہC�N���X�̓��e�Ɋւ���MD5�n�b�V���R�[�h�������o����܂��D����ł� classes.txt �ɏo�Ă���N���X�̖��O�͐�΃p�X�ɂȂ��Ă��܂��̂ŁC�o�͂��ꂽ�t�@�C����ʌv�Z�@�E�ʃf�B���N�g���Ɉړ����ĉ�͂���ꍇ�ɂ͒��ӂ��Ă��������D
  * LogLevel.IgnoreArrayInitializer: path/to/aClass.class �Ƃ������b�Z�[�W�́C�z��̏������i���Ƃ��� `new int[]{1, 2, 3}`�j�ɂ����鐔�l�̑�����������O�擾����O�����Ƃ��ɕ\������܂��D
  * LogLevel.OnlyEntryExit: path/to/aClass.class �Ƃ������b�Z�[�W�́C�ΏۃN���X�̂ǂꂩ�̃��\�b�h�����ɑ傫�����߁C���\�b�h�ւ̐i���ƒE�o�iENTRY, EXIT�j�݂̂��擾����悤�Ƀ��O�擾�R�[�h�𖄂ߍ��񂾏ꍇ�ɕ\������܂��D
  * Failed to weave ... �Ƃ������b�Z�[�W�́C���O�擾�R�[�h�����炩�̌����Ŗ��ߍ��߂Ȃ������ꍇ�ɕ\������܂��D���Ƃ���DaCapo�x���`�}�[�N�Ɏ��^����Ă��� org/apache/derbyTesting/databaseclassloader/emc.class �́C�e�X�g�p�ɍ��ꂽ�u��ꂽ�v�N���X�t�@�C���ł��D

������̃��b�Z�[�W�ł��C�����̑ΏۃN���X�ɂ��Ă͐��m�Ȍ��ʂ����߂��Ă��Ȃ���������Ȃ��C�Ƃ��������ɒ��ӂ��ĉ�͂��s���K�v������܂��i�����̏ꍇ�̓e�X�g�p�R�[�h��C����Ȕz��̏������e�[�u���������Ȃ̂ŁC�����܂Őr��ȉe���͂Ȃ��ł��傤���j�D


## ���O�̓��e

* �C�x���g�́C�X���b�h�Ɋ֌W�Ȃ��C���n��ŏ��Ԃɋl�ߍ��܂ꂽ�n��Ƃ��ċL�^����܂��D
* �e�C�x���g�ɂ́C�C�x���gID�C�R�[�h�ʒuID�C�X���b�hID���t���Ă��܂��D�����R�[�h�ʒu�𕡐��̃X���b�h���ʉ߂�����̂ŁC�X���b�hID�̊m�F�͕K�{�ł��D
  * �S���\�b�h�Ăяo���ɂ��āF�N���ithis�j�C�N�Ɂireceiver�j�C����
  * �S���\�b�h�Ăяo���̊����������_�ŁF�߂�l
  * �S���\�b�h�̎��s�擪�ŁF����(this�܂�)
  * �S���\�b�h�̎��s�������_(return)�Ŗ߂�l
  * �S���\�b�h�̗�O������(catch (Throwable))�ŗ�O�I�u�W�F�N�g
  * �z��̐����ɂ��āF�ł���������ID�C�z��̃T�C�Y�D
  * �������z��̐����ɂ��āF�ł�����������ԊO����ID�i����C���\��j
  * �z��Q�Ƃɂ��āF�N�Ɂitarget�j�C�C���f�N�X(index)�C�ǂݏ������ꂽ�l
  * �t�B�[���h�ɂ��āF�N�Ɂitarget�j�C�t�B�[���hID�C�ǂݏ������ꂽ�l


* �X���b�h���ƂɃC�x���g�𕪐͂���ƁC���̏����֌W��ۂD

        method ::= METHOD_ENTRY  FORMAL_PARAM*  instruction  (METHOD_NORMAL_EXIT|METHOD_EXCEPTIONAL_EXIT)
        instruction ::= METHOD_CALL  ACTUAL_PARAM*  method?  (RETURN_VALUE_AFTER_CALL|OBJECT_INITIALIZED|OBJECT_CREATION_COMPLETED)
                      | ARRAY_LOAD  ARRAY_LOAD_RESULT
                      | (GET_INSTANCE_FIELD|GET_STATIC_FIELD)  method?  EVENT_GET_FIELD_RESULT
                      | THROW
                      | ARRAY_STORE
                      | PUT_INSTANCE_FIELD
                      | PUT_INSTANCE_FIELD_BEFORE
                      | PUT_STATIC_FIELD
                      | NEW_ARRAY
                      | MULTI_NEW_ARRAY
                      | ARRAY_LENGTH ARRAY_LENGTH_RESULT
                      | INSTANCEOF


  * GET_INSTANCE_FIELD �Ȃǂɑ��� `method?` �́C�t�B�[���h�Q�Ƃɉ����ăN���X�̓ǂݍ��݂��������C`<clinit>`�����s���ꂽ�ꍇ�ɔ������܂��D
  * ��O���N�����ꍇ�́C���� catch �u���b�N������� `EVENT_CATCH` �Ƃ��ċL�^�����D�������O�̃C�x���g�� `ARRAY_LOAD, GET_*_FIELD, CALL` �ł���΁C����炪���s���Ă��邱�ƂɂȂ�܂��D
    * catch �u���b�N���Ȃ��ꍇ�� `METHOD_EXCEPTIONAL_EXIT` �ɓ��B����D���̂Ƃ������O�̃C�x���g�� `ARRAY_LOAD, GET_*_FIELD, CALL` �ł���΁C����炪���s���Ă��邱�ƂɂȂ�܂��D
  * -wave=ALL �ȂǂƂ���Weaver���s����LABEL�L�^��ON�ɂ��Ă����ƁC�������򓙂ɂ��uLABEL�v�̒ʉ߂��m�F���邱�Ƃ��ł��C�u�����`�J�o���b�W�̌v�Z�Ɏg�p�ł��܂��D


## ���O�ɋL�^����Ă��Ȃ����

* ���[�J���ϐ��̏�ԁD���\�b�h�̈����Ȃǂ���Č����邱�Ƃ͉\�ł��D
* �s�P�ʂł̖��߂̒ʉ߁D����t���[���͏����Ă���̂ŁC�����Ōv�Z���邱�Ƃ͉\�ł��D
