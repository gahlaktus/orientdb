package com.orientechnologies.orient.client.remote.message.tx;

import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.record.ORecord;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ORecordOperationRequest {
  private byte    type;
  private byte    recordType;
  private ORID    id;
  private ORID    oldId;
  private byte[]  record;
  private int     version;
  private boolean contentChanged;

  public ORID getId() {
    return id;
  }

  public void setId(ORID id) {
    this.id = id;
  }

  public ORID getOldId() {
    return oldId;
  }

  public void setOldId(ORID oldId) {
    this.oldId = oldId;
  }

  public byte[] getRecord() {
    return record;
  }

  public void setRecord(byte[] record) {
    this.record = record;
  }

  public byte getRecordType() {
    return recordType;
  }

  public void setRecordType(byte recordType) {
    this.recordType = recordType;
  }

  public byte getType() {
    return type;
  }

  public void setType(byte type) {
    this.type = type;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public void setContentChanged(boolean contentChanged) {
    this.contentChanged = contentChanged;
  }

  public boolean isContentChanged() {
    return contentChanged;
  }

  public void deserialize(DataInput input) throws IOException {
    type = input.readByte();
    recordType = input.readByte();
    id = ORecordId.deserialize(input);
    oldId = ORecordId.deserialize(input);
    int size = input.readInt();
    record = new byte[size];
    input.readFully(record);
    version = input.readInt();
    contentChanged = input.readBoolean();
  }

  public void serialize(DataOutput output) throws IOException {
    output.writeByte(type);
    output.writeByte(recordType);
    ORecordId.serialize(id, output);
    ORecordId.serialize(oldId, output);
    output.writeInt(record.length);
    output.write(record);
    output.writeInt(version);
    output.writeBoolean(contentChanged);
  }

}