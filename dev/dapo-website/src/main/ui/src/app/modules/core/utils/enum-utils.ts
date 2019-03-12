export class EnumUtils {

  public static  getValues(enumObject):Array<any> {
    let keys = Object.keys(enumObject);
    return keys.slice(keys.length / 2);
  }

}
